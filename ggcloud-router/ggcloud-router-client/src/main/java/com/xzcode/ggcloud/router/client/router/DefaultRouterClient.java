package com.xzcode.ggcloud.router.client.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.client.RouterClient;
import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceProvider;

import xzcode.ggserver.core.common.message.Pack;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultRouterClient implements RouterClient{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouterClient.class);
	
	private RouterClientConfig config;
	private IRouterServiceProvider serviceProvider;
	
	public DefaultRouterClient(RouterClientConfig config) {
		this.config = config;
		serviceProvider = config.getServiceProvider();
		this.config.init();
		config.setRouterClient(this);
	}
	
	@Override
	public boolean route(Pack pack) {
		try {
			IRouterService matchService = serviceProvider.matchService(pack);
			if (matchService != null) {
				matchService.dispatch(pack);
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Route Message Error!", e);
		}
		return false;
	}

}
