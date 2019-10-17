package xzcode.ggcloud.gateway.router.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggcloud.gateway.config.GGGatewayConfig;
import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggcloud.gateway.router.binding.RouterMessageDispacher;
import xzcode.ggcloud.gateway.router.resolve.provider.IRouterServiceProvider;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.server.GGServer;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements IGatewayRouter{

	@Override
	public boolean route(PackModel packModel, GGSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean switchService(IRouterService service, GGSession session) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
