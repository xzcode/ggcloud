package com.xzcode.ggcloud.router.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceProvider;

import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.server.IGGServer;

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
	private IGGServer routingServer;
	
	public DefaultRouterClient(RouterClientConfig config) {
		this.config = config;
		this.serviceProvider = config.getServiceProvider();
		this.routingServer = config.getRoutingServer();
		this.config.setRouterClient(this);
		this.config.init();
	}
	
	@Override
	public boolean route(Pack pack) {
		try {
			
			String actionId = pack.getActionString(config.getCharset());
			//routingServer已定义的actionid,不参与路由
			if (routingServer.getRequestMessageManager().getMessageHandler(actionId) != null) {
				return false;
			}
			
			//进行服务匹配
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
