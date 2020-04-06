package com.xzcode.ggcloud.eventbus.server;

import com.xzcode.ggcloud.eventbus.common.message.req.DiscoveryServiceListReq;
import com.xzcode.ggcloud.eventbus.common.message.req.EventSubscribeReq;
import com.xzcode.ggcloud.eventbus.common.message.req.DiscoveryServiceUpdateReq;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;
import com.xzcode.ggcloud.eventbus.server.events.ConnActiveEventListener;
import com.xzcode.ggcloud.eventbus.server.events.ConnCloseEventListener;
import com.xzcode.ggcloud.eventbus.server.handler.RegisterReqHandler;
import com.xzcode.ggcloud.eventbus.server.handler.ServiceListReqHandler;
import com.xzcode.ggcloud.eventbus.server.handler.ServiceUpdateReqHandler;

import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;
import xzcode.ggserver.core.server.impl.GGServer;

public class DiscoveryServer {
	
	private EventbusServerConfig config;
	
	
	
	public DiscoveryServer(EventbusServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		GGServerConfig ggConfig = new GGServerConfig();
		ggConfig.setPingPongEnabled(true);
		ggConfig.setPrintPingPongInfo(config.isPrintPingPongInfo());
		ggConfig.setProtocolType(ProtocolTypeConstants.TCP);
		ggConfig.setPort(config.getPort());
		ggConfig.setBossGroupThreadFactory(new GGThreadFactory("discovery-boss-", false));
		ggConfig.setWorkerGroupThreadFactory(new GGThreadFactory("discovery-worker-", false));
		ggConfig.init();
		IGGServer ggServer = new GGServer(ggConfig);
		
		ggServer.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener(config));
		
		ggServer.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		
		ggServer.onMessage(EventSubscribeReq.ACTION, new RegisterReqHandler(config));
		ggServer.onMessage(DiscoveryServiceListReq.ACTION, new ServiceListReqHandler(config));
		ggServer.onMessage(DiscoveryServiceUpdateReq.ACTION, new ServiceUpdateReqHandler(config));
		
		ggServer.start();
		
		config.setServer(ggServer);
		
	}
	
	public void setConfig(EventbusServerConfig config) {
		this.config = config;
	}
	
	public EventbusServerConfig getConfig() {
		return config;
	}
	
}
