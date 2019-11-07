package xzcode.ggcloud.gateway.router.impl;

import xzcode.ggcloud.gateway.config.GatewayRouterConfig;
import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggcloud.gateway.router.service.IRouterService;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements IGatewayRouter{
	
	private GatewayRouterConfig config;
	
	public DefaultGatewayRouter(GatewayRouterConfig config) {
		this.config = config;
	}
	
	@Override
	public IGGFuture route(GGSession session, Pack pack) {
		String action = pack.getActionString();
		IRouterService matchService = config.getServiceProvider().matchService(pack);
		
		return null;
	}

}
