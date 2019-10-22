package xzcode.ggcloud.gateway.router.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggcloud.gateway.config.GatewayRouterConfig;
import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggcloud.gateway.router.dispatcher.MessageDispatcher;
import xzcode.ggcloud.gateway.router.resolve.provider.IRouterServiceProvider;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.GGSessionUtil;
import xzcode.ggserver.core.server.GGServer;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements IGatewayRouter{
	
	private GatewayRouterConfig config;
	
	private Map<GGSession, MessageDispatcher> dispatchers = new ConcurrentHashMap<>();
	
	private GGServer routingServer;
	
	
	public DefaultGatewayRouter(GatewayRouterConfig config) {
		super();
		this.config = config;
	}
	
	public void init() {
		//连接端口事件处理
		routingServer.onEvent(GGEvents.Connection.CLOSE, (Void data) -> {
			GGSession session = GGSessionUtil.getSession();
			MessageDispatcher dispatcher = dispatchers.get(session);
			if (dispatcher != null) {
				dispatcher.disconnectService();
			}
		});
		//连接成功事件
		routingServer.onEvent(GGEvents.Connection.OPEN, (Void data) -> {
			GGSession session = GGSessionUtil.getSession();
			MessageDispatcher dispatcher = dispatchers.get(session);
			if (dispatcher == null) {
				dispatcher = new MessageDispatcher(session);
				dispatchers.putIfAbsent(session, dispatcher);
			}
		});
	}

	@Override
	public IGGFuture switchService(GGSession session, IRouterService service) {
		MessageDispatcher dispatcher = dispatchers.get(session);
		if (dispatcher != null) {
			return dispatcher.switchOrConnectService(service);
		}
		return null;
	}

	@Override
	public IGGFuture switchService(GGSession session, String serviceName) {
		IRouterService routerService = config.getServiceProvider().getService(serviceName);
		if (routerService != null) {
			MessageDispatcher dispatcher = dispatchers.get(session);
			if (dispatcher != null) {
				return dispatcher.switchOrConnectService(routerService);
			}
		}
		return null;
		
	}
	

}
