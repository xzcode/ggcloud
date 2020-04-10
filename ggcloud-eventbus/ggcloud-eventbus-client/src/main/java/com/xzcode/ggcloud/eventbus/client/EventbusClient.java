package com.xzcode.ggcloud.eventbus.client;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.session.group.client.SessionGroupClient;
import com.xzcode.ggcloud.session.group.client.config.SessionGroupClientConfig;

import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;

public class EventbusClient {
	
	private EventbusClientConfig config;
	
	
	public EventbusClient(EventbusClientConfig config) {
		this.config = config;
		this.config.setEventbusClient(this);
	}

	public void start() {
		
		SessionGroupClientConfig sessionGroupClientConfig = new SessionGroupClientConfig();
		sessionGroupClientConfig.setEnableServiceClient(true);
		sessionGroupClientConfig.setAuthToken(this.config.getAuthToken());
		sessionGroupClientConfig.setWorkThreadFactory(new GGThreadFactory("gg-evt-cli-", false));
		sessionGroupClientConfig.setConnectionSize(this.config.getConnectionSize());
		
		SessionGroupClient sessionGroupClient = new SessionGroupClient(sessionGroupClientConfig);
		sessionGroupClient.start();
		
	}
	
	
	

}
