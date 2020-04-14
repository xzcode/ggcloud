package com.xzcode.ggcloud.router.server;

import com.xzcode.ggcloud.discovery.client.DiscoveryClient;
import com.xzcode.ggcloud.router.common.constant.RouterServiceCustomDataKeys;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;

import xzcode.ggserver.core.common.future.IGGFuture;

/**
 * 路由服务器对象
 * 
 * @author zai
 * 2019-12-05 10:34:03
 */
public class RouterServer {
	
	private RouterServerConfig config;
	
	public RouterServer(RouterServerConfig config) {
		this.config = config;
		
		DiscoveryClient discoveryClient = config.getDiscoveryClient();
		if (discoveryClient != null) {
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_GROUP, config.getRouterGroupId());
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_ACTION_ID_PREFIX, config.getActionIdPrefix());
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_PORT, String.valueOf(config.getPort()));
		}
	}

	public RouterServerConfig getConfig() {
		return config;
	}

	@Override
	public IGGFuture start() {
		
		return startFuture;
	}
	
	
}
