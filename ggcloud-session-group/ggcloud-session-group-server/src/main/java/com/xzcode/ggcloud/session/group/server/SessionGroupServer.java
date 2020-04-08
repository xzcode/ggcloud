package com.xzcode.ggcloud.session.group.server;

import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;
import com.xzcode.ggcloud.session.group.common.message.req.AuthReq;
import com.xzcode.ggcloud.session.group.common.session.SessionGroupSessionFactory;
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

/**
 * 会话组服务器启动类
 *
 * @author zai
 * 2020-04-08 15:21:21
 */
public class SessionGroupServer {
	
	private SessionGroupServerConfig config;
	
	
	
	public SessionGroupServer(SessionGroupServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		GGThreadFactory bossThreadFactory = new GGThreadFactory("gg-boss-", false);
		GGThreadFactory workThreadFactory = new GGThreadFactory("gg-worker-", false);
		
		GGServerConfig sessionServerConfig = new GGServerConfig();
		sessionServerConfig.setPingPongEnabled(true);
		sessionServerConfig.setPrintPingPongInfo(config.isPrintPingPongInfo());
		sessionServerConfig.setProtocolType(ProtocolTypeConstants.TCP);
		sessionServerConfig.setPort(config.getPort());
		sessionServerConfig.setSessionFactory(new SessionGroupSessionFactory(sessionServerConfig));
		sessionServerConfig.setBossGroupThreadFactory(bossThreadFactory);
		sessionServerConfig.setWorkerGroupThreadFactory(workThreadFactory);
		sessionServerConfig.init();
		
		GGSessionGroupManager sessionGroupManager = new GGSessionGroupManager(sessionServerConfig);
		this.config.setSessionGroupManager(sessionGroupManager);
		
		IGGServer sessionServer = new GGServer(sessionServerConfig);
		sessionServer.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener(config));
		sessionServer.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		sessionServer.onMessage(AuthReq.ACTION, new AuthReqHandler(config));
		sessionServer.start();
		this.config.setSessionServer(sessionServer);
		
		
		GGServerConfig serviceServerConfig = new GGServerConfig();
		serviceServerConfig.setProtocolType(ProtocolTypeConstants.TCP);
		serviceServerConfig.setBossGroupThreadFactory(bossThreadFactory);
		serviceServerConfig.setWorkerGroupThreadFactory(workThreadFactory);
		serviceServerConfig.init();
		
		IGGServer serviceServer = new GGServer(serviceServerConfig);
		this.config.setSessionServer(serviceServer);
		
	}
	
	public void setConfig(SessionGroupServerConfig config) {
		this.config = config;
	}
	
	public SessionGroupServerConfig getConfig() {
		return config;
	}
	
}
