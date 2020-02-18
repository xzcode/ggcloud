package com.xzcode.ggcloud.router.client.router.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.event.RouterClientEvents;
import com.xzcode.ggcloud.router.client.pool.RouterChannelPoolHandler;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceMatcher;
import com.xzcode.ggcloud.router.client.router.service.listener.IRouterServiceActiveListener;
import com.xzcode.ggcloud.router.client.router.service.listener.IRouterServiceInActiveListener;

import io.netty.channel.Channel;
import io.netty.channel.pool.ChannelPool;
import io.netty.util.concurrent.DefaultThreadFactory;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.executor.ITaskExecutor;
import xzcode.ggserver.core.common.future.GGFailedFuture;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.manager.ISessionManager;

/**
 * 默认路由服务
 * 
 * @author zai
 * 2019-11-07 16:52:05
 */
public class DefaultRouterService implements IRouterService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouterService.class);
	
	protected RouterClientConfig config;
	
	protected String serviceId;
	
	protected String servcieName;
	
	protected String host;
	
	protected int port;
	
	protected IRouterServiceMatcher serviceMatcher;
	
	
	protected ITaskExecutor executor;
	
	
	protected IGGFuture checkConnectionsFuture;
	
	/**
	 * 绑定的连接客户端
	 */
	protected GGClient distClient;
	
	
	/**
	 * 是否已准备接收数据
	 */
	protected AtomicInteger avaliableConnections = new AtomicInteger(0);
	/**
	 * 额外数据
	 */
	protected Map<String, String> customData = new ConcurrentHashMap<>();
	
	/**
	 * 服务生效监听
	 */
	protected List<IRouterServiceActiveListener> activeListeners = new ArrayList<>();
	
	/**
	 * 服务失效监听器
	 */
	protected List<IRouterServiceInActiveListener> inActiveListeners = new ArrayList<>();
	
	/**
	 * 是否已关闭
	 */
	protected boolean shutdown;
	

	public DefaultRouterService(RouterClientConfig config, String serviceId) {
		this.config = config;
		this.serviceId = serviceId;
	}
	
	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-11-07 15:50:25
	 */
	public void init() {
		
		GGClientConfig clientConfig = new GGClientConfig();
		clientConfig.setWorkerGroupThreadFactory(new DefaultThreadFactory("router-service-" + this.serviceId + "-", false));
		clientConfig.setWorkerGroup(config.getRoutingServer().getConfig().getWorkerGroup());
		clientConfig.setMetadataResolver(config.getMetadataResolver());
		clientConfig.setMetadataProvider(config.getMetadataProvider());
		clientConfig.setChannelPoolHandler(new RouterChannelPoolHandler(config, clientConfig));
		clientConfig.setChannelPoolMaxSize(config.getRouterClientChannelPoolMaxSize());
		clientConfig.setHost(host);
		clientConfig.setPort(port);
		clientConfig.setChannelPoolEnabled(true);
		
		this.executor = config.getRoutingServer().getTaskExecutor().nextEvecutor();
		
		distClient = new GGClient(clientConfig);
		
		//监听连接打开
		distClient.addEventListener(GGEvents.Connection.OPENED, e -> {
			LOGGER.warn("RouterService[{}] Channel Opened: {}", config.getRouterGroup() , e.getChannel());
			avaliableConnections.incrementAndGet();
		});
		
		//监听连接关闭
		distClient.addEventListener(GGEvents.Connection.CLOSED, e -> {
			LOGGER.warn("RouterService[{}] Channel Closed: {}", config.getRouterGroup(), e.getChannel());
			int i = avaliableConnections.get();
			if (i > 0) {
				i = avaliableConnections.decrementAndGet();				
				if (i <= 0) {
					config.getRoutingServer().emitEvent(new EventData<IRouterService>(RouterClientEvents.RouterService.UNAVAILABLE, this));
				}
			}
		});
		
		distClient.addAfterSerializeFilter((Pack pack) -> {
			//对发送到远端的包进行处理
			config.getPackHandler().handleSendPack(pack);
			return true;
		});
		
		distClient.addBeforeDeserializeFilter((Pack pack) -> {
			//对远端返回的包进行处理
			config.getPackHandler().handleReceivePack(pack);
			return false;
		});
		
		startCheckConnectionsTask();
		
	}
	
	/**
	 * 初始化连接任务
	 * 
	 * @author zai
	 * 2020-01-10 19:03:05
	 */
	public void startCheckConnectionsTask() {
		
		this.checkConnectionsFuture = this.executor.scheduleWithFixedDelay(1, 5, TimeUnit.SECONDS, () -> {
			if (isShutdown()) {
				getCheckConnectionsFuture().cancel();
			}
			int need = config.getRouterClientChannelPoolMaxSize() - this.avaliableConnections.get(); 
			if (need > 0) {
				for (int i = 0; i < need; i++) {
					this.executor.submitTask(() -> {
						ChannelPool channelPool = distClient.getConfig().getChannelPool();
						channelPool.acquire()
						.addListener(f -> {
							Channel channel = (Channel) f.getNow();
							if (LOGGER.isDebugEnabled()) {
								LOGGER.info("Router Service Init Connections ----> [{}], channel: [{}]", getHost() + ":" + getPort(), channel);						
							}
							if (channel == null) {
								if (LOGGER.isInfoEnabled()) {
									LOGGER.info("Router Service Init Failed! ----> channel is null");						
								}
								return;
							}
							if (LOGGER.isInfoEnabled()) {
								LOGGER.info("Router Service Init Success! ----> channel: [{}]", channel);						
							}
							channelPool.release(channel);					
						});
					});
				}
			}
		});
	}
	
	/**
	 * 是否可用
	 * 
	 * @return
	 * @author zai
	 * 2020-02-07 11:08:07
	 */
	@Override
	public boolean isAvailable() {
		return this.avaliableConnections.get() >= config.getRouterClientChannelPoolMaxSize();
	}
	
	/**
	 * 转发消息
	 * 
	 * @param pack
	 * @author zai
	 * 2019-11-07 17:53:00
	 */
	public IGGFuture dispatch(Pack pack) {
		if (!isAvailable()) {
			return GGFailedFuture.DEFAULT_FAILED_FUTURE;
		}
		if (isShutdown()) {
			return GGFailedFuture.DEFAULT_FAILED_FUTURE;
		}
		return distClient.send(pack);
	}
	
	/**
	 * 关闭
	 * 
	 * @author zai
	 * 2019-11-07 15:51:04
	 */
	@Override
	public void shutdown() {
		this.shutdown = true;
		ISessionManager sessionManager = this.distClient.getSessionManager();
		sessionManager.clearAllSession();
		config.getRoutingServer().emitEvent(new EventData<IRouterService>(RouterClientEvents.RouterService.SHUTDOWN, this));
	}
	
	
	public void addActiveListener(IRouterServiceActiveListener listener) {
		this.activeListeners.add(listener);
	}
	
	public void addInActiveListener(IRouterServiceInActiveListener listener) {
		this.inActiveListeners.add(listener);
	}

	
	public void setServiceMatcher(IRouterServiceMatcher serviceMatcher) {
		this.serviceMatcher = serviceMatcher;
	}
	

	@Override
	public IRouterServiceMatcher getServiceMatcher() {
		return this.serviceMatcher;
	}
	
	@Override
	public String getServiceId() {
		return this.serviceId;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}
	

	@Override
	public String getExtraData(String key) {
		return this.customData.get(key);
	}
	
	public void removeExtraData(String key) {
		this.customData.remove(key);
	}
	public void addExtraData(String key, String data) {
		this.customData.put(key, data);
	}
	public void addAllExtraData(Map<String, String> extraData) {
		this.customData.putAll(extraData);
	}
	public void replaceExtraData(Map<String, String> extraData) {
		this.customData.clear();
		this.customData.putAll(extraData);
	}
	
	public Map<String, String> getExtraDatas() {
		return customData;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}
	public boolean isShutdown() {
		return shutdown;
	}
	public IGGFuture getCheckConnectionsFuture() {
		return checkConnectionsFuture;
	}
	public String getServcieName() {
		return servcieName;
	}
	
	public void setServcieName(String servcieName) {
		this.servcieName = servcieName;
	}


}
