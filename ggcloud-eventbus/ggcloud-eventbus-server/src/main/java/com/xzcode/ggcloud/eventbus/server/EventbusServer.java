package com.xzcode.ggcloud.eventbus.server;

import com.xzcode.ggcloud.eventbus.common.message.req.EventPublishReq;
import com.xzcode.ggcloud.eventbus.common.message.req.EventSubscribeReq;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;
import com.xzcode.ggcloud.eventbus.server.handler.EventPublishReqHandler;
import com.xzcode.ggcloud.eventbus.server.handler.EventSubscribeReqHandler;
import com.xzcode.ggcloud.session.group.server.SessionGroupServer;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.server.IGGServer;

public class EventbusServer {
	
	private EventbusServerConfig config;
	
	public EventbusServer(EventbusServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		SessionGroupServerConfig sessionGroupServerConfig = new SessionGroupServerConfig();
		sessionGroupServerConfig.setAuthToken(this.config.getAuthToken());
		sessionGroupServerConfig.setEnableServiceServer(true);
		sessionGroupServerConfig.setPort(this.config.getPort());
		sessionGroupServerConfig.setWorkThreadSize(this.config.getWorkThreadSize());
		sessionGroupServerConfig.setPrintPingPongInfo(this.config.isPrintPingPongInfo());
		sessionGroupServerConfig.setWorkThreadFactory(new GGThreadFactory("gg-evt-serv-", false));
		
		SessionGroupServer sessionGroupServer = new SessionGroupServer(sessionGroupServerConfig);
		this.config.setSessionGroupServer(sessionGroupServer);
		
		IGGServer serviceServer = sessionGroupServerConfig.getServiceServer();
		
		serviceServer.onMessage(EventPublishReq.ACTION_ID, new EventPublishReqHandler(config));
		serviceServer.onMessage(EventSubscribeReq.ACTION_ID, new EventSubscribeReqHandler(config));
		
		sessionGroupServer.start();
	}
	
	public void setConfig(EventbusServerConfig config) {
		this.config = config;
	}
	
	public EventbusServerConfig getConfig() {
		return config;
	}
	
}
