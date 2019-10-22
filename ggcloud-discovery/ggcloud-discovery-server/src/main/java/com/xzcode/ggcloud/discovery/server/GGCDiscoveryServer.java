package com.xzcode.ggcloud.discovery.server;

import com.xzcode.ggcloud.discovery.common.message.req.RegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.ReportReq;
import com.xzcode.ggcloud.discovery.server.config.GGCDiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.events.ConnActiveEventHandler;
import com.xzcode.ggcloud.discovery.server.events.ConnCloseEventHandler;
import com.xzcode.ggcloud.discovery.server.handler.RegisterReqHandler;

import nonapi.io.github.classgraph.concurrency.SimpleThreadFactory;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.server.GGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;

public class GGCDiscoveryServer {
	
	private GGCDiscoveryServerConfig config;
	
	
	
	public GGCDiscoveryServer(GGCDiscoveryServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		GGServerConfig ggConfig = new GGServerConfig();
		ggConfig.setPort(config.getPort());
		ggConfig.setBossGroupThreadFactory(new SimpleThreadFactory("discovery-B-", false));
		ggConfig.setWorkerGroupThreadFactory(new SimpleThreadFactory("discovery-W-", false));
		ggConfig.init();
		GGServer ggServer = new GGServer(ggConfig);
		
		ggServer.onEvent(GGEvents.Connection.OPEN, new ConnActiveEventHandler());
		
		ggServer.onEvent(GGEvents.Connection.CLOSE, new ConnCloseEventHandler());
		
		ggServer.onMessage(RegisterReq.ACTION, new RegisterReqHandler(config));
		ggServer.onMessage(ReportReq.ACTION, new RegisterReqHandler(config));
		
		ggServer.start();
		
		config.setGgServer(ggServer);
		
	}
	
	public void setConfig(GGCDiscoveryServerConfig config) {
		this.config = config;
	}
	
	
}
