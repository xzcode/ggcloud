package xzcode.ggcloud.gateway.router.service.impl;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.util.concurrent.DefaultThreadFactory;
import xzcode.ggcloud.gateway.config.GatewayRouterConfig;
import xzcode.ggcloud.gateway.router.service.IRouterService;
import xzcode.ggcloud.gateway.router.service.IRouterServiceMatcher;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.filter.IBeforeDeserializeFilter;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认路由服务
 * 
 * @author zai
 * 2019-11-07 16:52:05
 */
public class DefaultRouterService implements IRouterService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouterService.class);
	
	protected GatewayRouterConfig config;
	
	protected String serviceId;
	
	protected String host;
	
	protected IRouterServiceMatcher serviceMatcher;
	
	
	protected int port;
	
	/**
	 * 消息队列
	 */
	protected Queue<Pack> packQueue = new ConcurrentLinkedQueue<>();
	
	/**
	 * 已连接的session集合
	 */
	protected List<GGSession> dispatchSessionList = new CopyOnWriteArrayList<>();
	
	/**
	 * 绑定的连接客户端
	 */
	protected GGClient dispatchClient;
	
	/**
	 * 重连中
	 */
	protected boolean reconnecting;
	
	/**
	 * 是否已关闭
	 */
	protected boolean down;

	private IGGFuture connectCheckFuture;

	private IGGFuture consumeMessageFuture;
	

	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-11-07 15:50:25
	 */
	public void init() {
		
		GGClientConfig clientConfig = new GGClientConfig();
		clientConfig.setWorkerGroupThreadFactory(new DefaultThreadFactory("router-service-" + this.serviceId + "-", false));
		clientConfig.setWorkThreadSize(config.getServiceConnectionSize() * 2);
		GGClient client = new GGClient(clientConfig);
		
		/**
		 * 监听连接打开事件
		 */
		client.addEventListener(GGEvents.Connection.OPEN, (EventData<Void> data) -> {
			GGSession session = data.getSession();
			dispatchSessionList.add(session);
			
		});
		
		/**
		 * 监听连接断开事件
		 */
		client.addEventListener(GGEvents.Connection.CLOSE, (EventData<Void> data) -> {
			GGSession session = data.getSession();
			dispatchSessionList.remove(session);
		});
		
		client.addBeforeDeserializeFilter(new IBeforeDeserializeFilter() {
			@Override
			public boolean doFilter(GGSession session, Pack data) {
				config.getRoutingServer().send(data);
				return false;
			}
		});
		
		this.dispatchClient = client;
		
		this.packQueue.clear();
		
		//开启连接检查任务
		this.startConnectCheckTask();
		
		//开始消费消息任务
		this.startConsumeMessageTask();
		
	}
	
	/**
	 * 转发消息
	 * 
	 * @param pack
	 * @author zai
	 * 2019-11-07 17:53:00
	 */
	public void dispatch(Pack pack) {
		//消息进入队列
		packQueue.add(pack);
	}
	
	/**
	 * 开启连接检查任务
	 * 
	 * @author zai
	 * 2019-11-07 15:44:45
	 */
	private void startConnectCheckTask() {
		this.connectCheckFuture = dispatchClient.scheduleWithFixedDelay(0L, config.getServiceReconnectDelayMs(), () -> {
			try {
				int reconnectSize = config.getServiceConnectionSize() - dispatchSessionList.size();
				if (reconnectSize != 0 && !this.reconnecting) {
					synchronized (this) {
						if (this.down) {
							return;
						}
						reconnectSize = config.getServiceConnectionSize() - dispatchSessionList.size();
						if (reconnectSize != 0 && !this.reconnecting) {
							this.reconnecting = true;
							for (int i = 0; i < reconnectSize; i++) {
								GGSession session = dispatchClient.connect(this.host, this.port);
								this.dispatchSessionList.add(session);
							}
							this.reconnecting = false;
						}
					}
				}
			} catch (Exception e) {
				LOGGER.error("Connection Check Task Error!", e);
			}
		});
	}
	
	/**
	 * 消费消息
	 * 
	 * 
	 * @author zai
	 * 2019-11-11 21:43:51
	 */
	private void startConsumeMessageTask() {
		
		this.consumeMessageFuture = dispatchClient.scheduleWithFixedDelay(1L, 5L, TimeUnit.MILLISECONDS, () -> {
			try {
				if (this.down) {
					return;
				}
				Pack pack = packQueue.poll();
				
				while (pack != null) {
					GGSession session = null;
					while (!this.down) {
						
						//如果没有可用的连接
						if (dispatchSessionList.size() == 0) {
							//清除队列
							packQueue.clear();
							
							return;
						}
						session = dispatchSessionList.get(ThreadLocalRandom.current().nextInt(dispatchSessionList.size()));
						if (session.isActive()) {
							session.send(pack);
						}else {
							dispatchSessionList.remove(session);
							session.disconnect();
						}
					}
				}
			} catch (Exception e) {
				LOGGER.error("Consume Message Error!", e);
			}
		});
	}
	
	/**
	 * 关闭
	 * 
	 * @author zai
	 * 2019-11-07 15:51:04
	 */
	public void shutdown() {
		this.down = true;
		this.dispatchClient.shutdown();
		this.connectCheckFuture.cancel();
		this.consumeMessageFuture.cancel();
		this.packQueue.clear();
		
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


	public void setId(String id) {
		this.serviceId = id;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
