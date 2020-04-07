package com.xzcode.ggcloud.eventbus.server;

import com.xzcode.ggcloud.eventbus.common.message.req.AuthReq;
import com.xzcode.ggcloud.eventbus.common.message.req.EventPublishReq;
import com.xzcode.ggcloud.eventbus.common.message.req.EventSubscribeReq;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;
import com.xzcode.ggcloud.eventbus.server.events.ConnActiveEventListener;
import com.xzcode.ggcloud.eventbus.server.events.ConnCloseEventListener;
import com.xzcode.ggcloud.eventbus.server.handler.AnthReqHandler;
import com.xzcode.ggcloud.eventbus.server.handler.EventPublishReqHandler;
import com.xzcode.ggcloud.eventbus.server.handler.EventSubscribeReqHandler;

import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;
import xzcode.ggserver.core.server.impl.GGServer;

public class EventbusServer {
	
	private EventbusServerConfig config;
	
	
	
	public EventbusServer(EventbusServerConfig config) {
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
		
		ggServer.onMessage(AuthReq.ACTION, new AnthReqHandler(config));
		ggServer.onMessage(EventPublishReq.ACTION, new EventPublishReqHandler(config));
		ggServer.onMessage(EventSubscribeReq.ACTION, new EventSubscribeReqHandler(config));
		
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
