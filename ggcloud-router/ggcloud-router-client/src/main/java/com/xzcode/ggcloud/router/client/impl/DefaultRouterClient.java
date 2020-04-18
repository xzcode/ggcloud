package com.xzcode.ggcloud.router.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.client.RouterClient;
import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceProvider;
import com.xzcode.ggserver.core.common.future.GGFailedFuture;
import com.xzcode.ggserver.core.common.future.IGGFuture;
import com.xzcode.ggserver.core.common.message.Pack;
import com.xzcode.ggserver.core.server.GGServer;

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
	
	public DefaultRouterClient(RouterClientConfig config) {
		this.config = config;
		this.config.setRouterClient(this);
		this.config.init();
	}
	
	@Override
	public IGGFuture route(Pack pack) {
		try {
			//进行服务匹配
			IRouterService matchService = config.getServiceProvider().matchService(pack);
			if (matchService != null) {
				return matchService.dispatch(pack);
			}
		} catch (Exception e) {
			LOGGER.error("Route Message Error!", e);
		}
		return GGFailedFuture.DEFAULT_FAILED_FUTURE;
	}

}
