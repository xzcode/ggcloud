package com.xzcode.ggcloud.eventbus.client;

import java.util.ArrayList;
import java.util.List;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.client.listener.IClientRegisterSuccessListener;
import com.xzcode.ggcloud.session.group.client.SessionGroupClient;
import com.xzcode.ggcloud.session.group.client.config.SessionGroupClientConfig;

import xzcode.ggserver.core.common.executor.DefaultTaskExecutor;

public class EventbusClient {
	
	private EventbusClientConfig config;
	
	protected List<IClientRegisterSuccessListener> registerSuccessListeners = new ArrayList<>();
	
	public EventbusClient(EventbusClientConfig config) {
		this.config = config;
		this.config.setEventbusClient(this);
	}

	public void start() {
		
		SessionGroupClientConfig sessionGroupClientConfig = new SessionGroupClientConfig();
		sessionGroupClientConfig.setAuthToken(this.config.getAuthToken());
		sessionGroupClientConfig.setTaskExecutor(new DefaultTaskExecutor("gg-evt-cli-", this.config.getWorkThreadSize()));
		sessionGroupClientConfig.setConnectionSize(this.config.getConnectionSize());
		sessionGroupClientConfig.setClientReportInterval(clientReportInterval);
		
		SessionGroupClient sessionGroupClient = new SessionGroupClient(sessionGroupClientConfig);
		
	}
	
	
	

}
