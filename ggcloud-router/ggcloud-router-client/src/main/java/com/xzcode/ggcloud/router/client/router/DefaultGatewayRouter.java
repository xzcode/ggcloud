package com.xzcode.ggcloud.router.client.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.client.config.RouterConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;

import xzcode.ggserver.core.common.message.Pack;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements RouterClient{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGatewayRouter.class);
	
	private RouterConfig config;
	
	public DefaultGatewayRouter(RouterConfig config) {
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
