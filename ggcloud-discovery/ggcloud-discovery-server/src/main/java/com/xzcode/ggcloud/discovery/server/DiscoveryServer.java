package com.xzcode.ggcloud.discovery.server;

import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryRegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryReportReq;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.events.ConnActiveEventListener;
import com.xzcode.ggcloud.discovery.server.events.ConnCloseEventListener;
import com.xzcode.ggcloud.discovery.server.handler.RegisterReqHandler;

import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;
import xzcode.ggserver.core.server.impl.GGServer;

public class DiscoveryServer {
	
	private DiscoveryServerConfig config;
	
	
	
	public DiscoveryServer(DiscoveryServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		GGServerConfig ggConfig = new GGServerConfig();
		ggConfig.setPingPongEnabled(true);
		ggConfig.setProtocolType(ProtocolTypeConstants.TCP);
		ggConfig.setPort(config.getPort());
		ggConfig.setBossGroupThreadFactory(new GGThreadFactory("discovery-boss-", false));
		ggConfig.setWorkerGroupThreadFactory(new GGThreadFactory("discovery-worker-", false));
		ggConfig.init();
		IGGServer ggServer = new GGServer(ggConfig);
		
		ggServer.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener(config));
		
		ggServer.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		
		ggServer.onMessage(DiscoveryRegisterReq.ACTION, new RegisterReqHandler(config));
		ggServer.onMessage(DiscoveryReportReq.ACTION, new RegisterReqHandler(config));
		
		ggServer.start();
		
		config.setServer(ggServer);
		
	}
	
	public void setConfig(DiscoveryServerConfig config) {
		this.config = config;
	}
	
	public DiscoveryServerConfig getConfig() {
		return config;
	}
	
}
