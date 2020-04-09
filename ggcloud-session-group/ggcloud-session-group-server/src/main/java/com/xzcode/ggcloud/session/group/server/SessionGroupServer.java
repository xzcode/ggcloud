package com.xzcode.ggcloud.session.group.server;

import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;
import com.xzcode.ggcloud.session.group.common.message.req.AuthReq;
import com.xzcode.ggcloud.session.group.common.message.req.DataTransferReq;
import com.xzcode.ggcloud.session.group.common.message.req.SessionGroupRegisterReq;
import com.xzcode.ggcloud.session.group.common.session.SessionGroupSessionFactory;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;
import com.xzcode.ggcloud.session.group.server.events.ConnActiveEventListener;
import com.xzcode.ggcloud.session.group.server.events.ConnCloseEventListener;
import com.xzcode.ggcloud.session.group.server.handler.AuthReqHandler;
import com.xzcode.ggcloud.session.group.server.handler.DataTransferReqHandler;
import com.xzcode.ggcloud.session.group.server.handler.SessionGroupRegisterReqHandler;

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
		
		GGThreadFactory bossThreadFactory = new GGThreadFactory("gg-group-boss-", false);
		GGThreadFactory workThreadFactory = new GGThreadFactory("gg-group-worker-", false);
		
		GGServerConfig sessionServerConfig = new GGServerConfig();
		sessionServerConfig.setPingPongEnabled(true);
		sessionServerConfig.setPrintPingPongInfo(config.isPrintPingPongInfo());
		sessionServerConfig.setProtocolType(ProtocolTypeConstants.TCP);
		sessionServerConfig.setPort(config.getPort());
		sessionServerConfig.setSessionFactory(new SessionGroupSessionFactory(sessionServerConfig));
		sessionServerConfig.setBossGroupThreadFactory(bossThreadFactory);
		sessionServerConfig.setWorkerGroupThreadFactory(workThreadFactory);
		sessionServerConfig.setWorkThreadSize(this.config.getWorkThreadSize());
		sessionServerConfig.init();
		
		GGSessionGroupManager sessionGroupManager = new GGSessionGroupManager(sessionServerConfig);
		this.config.setSessionGroupManager(sessionGroupManager);
		
		IGGServer sessionServer = new GGServer(sessionServerConfig);
		sessionServer.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener(config));
		sessionServer.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		sessionServer.onMessage(AuthReq.ACTION, new AuthReqHandler(config));
		sessionServer.onMessage(SessionGroupRegisterReq.ACTION_ID, new SessionGroupRegisterReqHandler(config));
		sessionServer.onMessage(DataTransferReq.ACTION, new DataTransferReqHandler(config));
		sessionServer.start();
		this.config.setSessionServer(sessionServer);
		
		
		GGServerConfig serviceServerConfig = new GGServerConfig();
		serviceServerConfig.setBossGroup(sessionServerConfig.getBossGroup());
		serviceServerConfig.setWorkerGroup(sessionServerConfig.getWorkerGroup());
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
