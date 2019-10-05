package com.xzcode.ggcloud.discovery.server;

import com.xzcode.ggcloud.discovery.common.message.req.RegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.ReportReq;
import com.xzcode.ggcloud.discovery.server.config.GGCDiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.events.ConnActiveEventHandler;
import com.xzcode.ggcloud.discovery.server.events.ConnCloseEventHandler;
import com.xzcode.ggcloud.discovery.server.handler.RegisterReqHandler;

import nonapi.io.github.classgraph.concurrency.SimpleThreadFactory;
import xzcode.ggserver.core.GGServer;
import xzcode.ggserver.core.config.GGConfig;
import xzcode.ggserver.core.event.GGEvents;

public class GGCDiscoveryServer {
	
	private GGCDiscoveryServerConfig config;
	
	
	
	public GGCDiscoveryServer(GGCDiscoveryServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		GGConfig ggConfig = new GGConfig();
		ggConfig.setBossGroupThreadFactory(new SimpleThreadFactory("discovery-B-", false));
		ggConfig.setWorkerGroupThreadFactory(new SimpleThreadFactory("discovery-W-", false));
		ggConfig.init();
		GGServer ggServer = new GGServer(ggConfig);
		
		ggServer.onEvent(GGEvents.ConnectionState.ACTIVE, new ConnActiveEventHandler());
		
		ggServer.onEvent(GGEvents.ConnectionState.CLOSE, new ConnCloseEventHandler());
		
		ggServer.onMessage(RegisterReq.ACTION, new RegisterReqHandler(config));
		ggServer.onMessage(ReportReq.ACTION, new RegisterReqHandler(config));
		
		ggServer.start();
		
		config.setGgServer(ggServer);
		
	}
	
	public void setConfig(GGCDiscoveryServerConfig config) {
		this.config = config;
	}
	
	
}
