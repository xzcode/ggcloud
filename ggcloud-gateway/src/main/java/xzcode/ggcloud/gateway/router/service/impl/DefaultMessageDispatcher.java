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
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.filter.IBeforeDeserializeFilter;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 路由消息转发器
 * 
 * @author zai
 * 2019-10-12 16:36:40
 */
public class DefaultMessageDispatcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageDispatcher.class);
	
	protected GatewayRouterConfig config;
	
	/**
	 * 路由服务对象
	 */
	protected Queue<Pack> packQueue = new ConcurrentLinkedQueue<>();
	
	/**
	 * 路由服务对象
	 */
	protected IRouterService routerService;
	
	/**
	 * 绑定的连接客户端
	 */
	protected GGClient dispatchClient;
	
	/**
	 * 已连接的session集合
	 */
	protected List<GGSession> dispatchSessionList = new CopyOnWriteArrayList<>();;
	
	/**
	 * 重连中
	 */
	protected boolean reconnecting;
	
	
	public DefaultMessageDispatcher(IRouterService routerService, GatewayRouterConfig config) {
		this.config = config;
		this.routerService = routerService;
	}

	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-11-07 15:50:25
	 */
	public void init() {
		
		GGClientConfig clientConfig = new GGClientConfig();
		clientConfig.setWorkerGroupThreadFactory(new DefaultThreadFactory("router-service-" + routerService.getServiceId() + "-", false));
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
		
		//开启连接检查任务
		this.startConnectCheckTask();
		
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
		dispatchClient.scheduleWithFixedDelay(0L, config.getServiceReconnectDelayMs(), () -> {
			try {
				int reconnectSize = config.getServiceConnectionSize() - dispatchSessionList.size();
				if (reconnectSize != 0 && !this.reconnecting) {
					synchronized (this) {
						reconnectSize = config.getServiceConnectionSize() - dispatchSessionList.size();
						if (reconnectSize != 0 && !this.reconnecting) {
							this.reconnecting = true;
							for (int i = 0; i < reconnectSize; i++) {
								dispatchClient.connect(routerService.getHost(), routerService.getPort());
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
	
	private void startConsumeMessageTask() {
		
		dispatchClient.scheduleWithFixedDelay(1L, 5L, TimeUnit.MILLISECONDS, () -> {
			try {
				
				Pack pack = packQueue.poll();
				
				while (pack != null) {
					GGSession session = null;
					while (true) {
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
	 * 关闭整个转发器
	 * 
	 * @author zai
	 * 2019-11-07 15:51:04
	 */
	public void shutdown() {
		this.dispatchClient.shutdown();
	}
	
	

}
