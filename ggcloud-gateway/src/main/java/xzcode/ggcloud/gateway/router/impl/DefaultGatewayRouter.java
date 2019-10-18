package xzcode.ggcloud.gateway.router.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggcloud.gateway.router.binding.RouterMessageDispatcher;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements IGatewayRouter{
	
	private Map<GGSession, RouterMessageDispatcher> dispatchers = new ConcurrentHashMap<>();
	
	@Override
	public void switchService(IRouterService service, GGSession session) {
		RouterMessageDispatcher dispatcher = dispatchers.get(session);
		if (dispatcher != null) {
			dispatcher.switchOrConnectService(service);
		}
	}
	

}
