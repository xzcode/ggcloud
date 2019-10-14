package xzcode.ggcloud.gateway.router.binding;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterResolver;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 路由会话绑定
 * 
 * @author zai
 * 2019-10-12 16:36:40
 */
public class RouterSessionBinding {
	

	/**
	 * 绑定的session
	 */
	protected GGSession bindingSession;
	
	/**
	 * 路由解析器对象
	 */
	protected IRouterResolver routerResolver;
	
	
	/**
	 * 绑定的连接客户端
	 */
	protected GGClient client;
	
	
	
	
	public RouterSessionBinding(GGSession bindingSession, IRouterResolver routerResolver) {
		this.bindingSession = bindingSession;
		this.routerResolver = routerResolver;
	}

	public void switchResolver(IRouterResolver routerResolver) {
		this.routerResolver = routerResolver;
	}
	
	public void connect() {
		
		GGClientConfig config = new GGClientConfig();
		config.init();
		GGClient client = new GGClient(config);
		
		//注册连接成功事件
		client.onEvent(GGEvents.ConnectionState.ACTIVE, (e) -> {
			System.out.println("连接成功");
		});
		//注册连接关闭事件
		client.onEvent(GGEvents.ConnectionState.CLOSE, (e) -> {
			System.out.println("连接关闭");
		});
		
		client.connect(routerResolver.getHost(), routerResolver.getPort());
	}
	
	public void routeMessage(PackModel pack) {
		this.client.send(pack);
	}
	
	public void disconnect() {
		client.disconnect();
	}
	
	public GGClient getClient() {
		return client;
	}

}
