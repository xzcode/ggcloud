package xzcode.ggcloud.gateway.router.dispatcher;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 路由会话绑定
 * 
 * @author zai
 * 2019-10-12 16:36:40
 */
public class MessageDispatcher {
	
	/**
	 * 来源session
	 */
	protected GGSession srcSession;
	
	/**
	 * 路由解析器对象
	 */
	protected IRouterService routerService;
	
	/**
	 * 绑定的连接客户端
	 */
	protected GGClient destClient;
	
	
	public void init() {
		srcSession.addBeforeDeserializeFilter((GGSession session, PackModel data) -> {
			if (getDestClient() != null) {
				getDestClient().send(data);						
			}
			return false;
		});
	}
	
	public MessageDispatcher(GGSession srcSession) {
		this.srcSession = srcSession;
		init();
	}

	public IGGFuture connectService(IRouterService routerService) {
		this.routerService = routerService;
		return connect();
	}
	public IGGFuture switchOrConnectService(IRouterService routerService) {
		this.routerService = routerService;
		return connect();
	}
	
	public void disconnectService() {
		destClient.disconnect();
	}
	
	public IGGFuture connect() {
		
		GGClientConfig config = new GGClientConfig();
		config.init();
		GGClient client = new GGClient(config);
		IGGFuture future = client.connect(routerService.getHost(), routerService.getPort());
		
		
		//注册连接成功事件
		client.onEvent(GGEvents.Connection.OPEN, (e) -> {
			synchronized (this) {
				if (client != destClient && destClient != null) {
					destClient.disconnect();				
				}
				destClient = client;
			}
		});
		
		//注册连接关闭事件
		client.onEvent(GGEvents.Connection.CLOSE, (e) -> {
			synchronized (this) {
				if (client == destClient) {
					srcSession.disconnect();
					return;
				}
			}
		});
		
		client.addBeforeDeserializeFilter((PackModel data) -> {
			srcSession.send(data);
			return false;
		});
		
		return future;
	}
	
	private GGClient getDestClient() {
		return destClient;
	}
	

}