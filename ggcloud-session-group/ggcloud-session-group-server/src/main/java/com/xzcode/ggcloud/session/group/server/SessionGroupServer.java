package com.xzcode.ggcloud.session.group.server;

import com.xzcode.ggcloud.session.group.common.message.req.AuthReq;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;
import com.xzcode.ggcloud.session.group.server.events.ConnActiveEventListener;
import com.xzcode.ggcloud.session.group.server.events.ConnCloseEventListener;
import com.xzcode.ggcloud.session.group.server.handler.AuthReqHandler;

import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;
import xzcode.ggserver.core.server.impl.GGServer;

public class SessionGroupServer {
	
	private SessionGroupServerConfig config;
	
	
	
	public SessionGroupServer(SessionGroupServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		GGServerConfig ggConfig = new GGServerConfig();
		ggConfig.setPingPongEnabled(true);
		ggConfig.setPrintPingPongInfo(config.isPrintPingPongInfo());
		ggConfig.setProtocolType(ProtocolTypeConstants.TCP);
		ggConfig.setPort(config.getPort());
		ggConfig.setBossGroupThreadFactory(new GGThreadFactory("gg-boss-", false));
		ggConfig.setWorkerGroupThreadFactory(new GGThreadFactory("gg-worker-", false));
		ggConfig.init();
		
		
		
		IGGServer sessionServer = new GGServer(ggConfig);
		sessionServer.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener(config));
		sessionServer.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		sessionServer.onMessage(AuthReq.ACTION, new AuthReqHandler(config));
		sessionServer.start();
		config.setSessionServer(sessionServer);
		
	}
	
	public void setConfig(SessionGroupServerConfig config) {
		this.config = config;
	}
	
	public SessionGroupServerConfig getConfig() {
		return config;
	}
	
}
