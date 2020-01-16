package com.xzcode.ggcloud.discovery.server;

import com.xzcode.ggcloud.discovery.common.message.req.RegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.ReportReq;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.events.ConnActiveEventListener;
import com.xzcode.ggcloud.discovery.server.events.ConnCloseEventListener;
import com.xzcode.ggcloud.discovery.server.handler.RegisterReqHandler;

import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.SimpleThreadFactory;
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
		ggConfig.setPort(config.getPort());
		ggConfig.setBossGroupThreadFactory(new SimpleThreadFactory("discovery-B-", false));
		ggConfig.setWorkerGroupThreadFactory(new SimpleThreadFactory("discovery-W-", false));
		ggConfig.init();
		IGGServer ggServer = new GGServer(ggConfig);
		
		ggServer.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener());
		
		ggServer.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener());
		
		ggServer.onMessage(RegisterReq.ACTION, new RegisterReqHandler(config));
		ggServer.onMessage(ReportReq.ACTION, new RegisterReqHandler(config));
		
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
