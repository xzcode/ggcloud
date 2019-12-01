package xzcode.ggcloud.gateway.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzcode.ggcloud.gateway.config.GatewayRouterConfig;
import xzcode.ggcloud.gateway.router.service.IRouterService;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGatewayRouter.class);
	
	private GatewayRouterConfig config;
	
	public DefaultGatewayRouter(GatewayRouterConfig config) {
		this.config = config;
	}
	
	@Override
	public void route(Pack pack) {
		try {
			Object metadata = null;
			pack.setMetadata(config.getRoutingServer().getConfig().getSerializer().serialize(metadata));
			IRouterService matchService = config.getServiceProvider().matchService(pack);
			if (matchService != null) {
				matchService.dispatch(pack);
			}
			
		} catch (Exception e) {
			LOGGER.error("Route Message Error!", e);
		}
	}

}
