package com.xzcode.ggcloud.detector.server.server;

import com.xzcode.ggcloud.detector.common.message.model.req.ClientRegisterReq;
import com.xzcode.ggcloud.detector.server.events.ConnActiveEventHandler;
import com.xzcode.ggcloud.detector.server.events.ConnCloseEventHandler;
import com.xzcode.ggcloud.detector.server.handler.ClientRegisterReqHandler;
import xzcode.ggserver.core.GGServer;
import xzcode.ggserver.core.config.GGConfig;
import xzcode.ggserver.core.event.GGEvents;

public class GGServerService {
	
	private GGServer ggServer;
	
	public void init() {
		
		GGConfig ggConfig = new GGConfig();
		ggServer = new GGServer(ggConfig);
		
		
		ggServer.onEvent(GGEvents.ConnectionState.ACTIVE, new ConnActiveEventHandler());
		
		ggServer.onEvent(GGEvents.ConnectionState.CLOSE, new ConnCloseEventHandler());
		
		ggServer.onMessage(ClientRegisterReq.ACTION, new ClientRegisterReqHandler());
		
		ggServer.start();
		
	}
	
	public GGServer getGgServer() {
		return ggServer;
	}
	
}
