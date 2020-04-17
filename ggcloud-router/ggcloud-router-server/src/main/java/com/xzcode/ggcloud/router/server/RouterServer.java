package com.xzcode.ggcloud.router.server;

import com.xzcode.ggcloud.discovery.client.DiscoveryClient;
import com.xzcode.ggcloud.router.common.constant.RouterServiceCustomDataKeys;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.session.group.server.SessionGroupServer;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.server.IGGServer;

/**
 * 路由服务器对象
 * 
 * @author zai
 * 2019-12-05 10:34:03
 */
public class RouterServer {
	
	private RouterServerConfig config;
	
	private IGGServer serviceServer;
	
	public RouterServer(RouterServerConfig config) {
		
		this.config = config;
		
		DiscoveryClient discoveryClient = config.getDiscoveryClient();
		if (discoveryClient != null) {
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_GROUP, config.getRouterGroupId());
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_ACTION_ID_PREFIX, config.getActionIdPrefix());
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_PORT, String.valueOf(config.getPort()));
		}
	}
	
	
	public void init() {
		
		SessionGroupServerConfig sessionGroupServerConfig = new SessionGroupServerConfig();
		sessionGroupServerConfig.setAuthToken(this.config.getAuthToken());
		sessionGroupServerConfig.setEnableServiceServer(true);
		sessionGroupServerConfig.setPort(this.config.getPort());
		sessionGroupServerConfig.setWorkThreadSize(this.config.getWorkThreadSize());
		sessionGroupServerConfig.setPrintPingPongInfo(this.config.isPrintPingPongInfo());
		sessionGroupServerConfig.setWorkThreadFactory(new GGThreadFactory("gg-evt-serv-", false));
		
		if (this.config.getSharedEventLoopGroup() != null) {
			sessionGroupServerConfig.setWorkEventLoopGroup(this.config.getSharedEventLoopGroup());
			
		}
		
		SessionGroupServer sessionGroupServer = new SessionGroupServer(sessionGroupServerConfig);
		this.config.setSessionGroupServer(sessionGroupServer);
		
		this.serviceServer = sessionGroupServerConfig.getServiceServer();
		
		sessionGroupServer.start();
		
	}
	
	public IGGServer getServiceServer() {
		return serviceServer;
	}

	public RouterServerConfig getConfig() {
		return config;
	}

	public IGGFuture start() {
		return this.config.getSessionGroupServer().start();
	}
	
	
}
