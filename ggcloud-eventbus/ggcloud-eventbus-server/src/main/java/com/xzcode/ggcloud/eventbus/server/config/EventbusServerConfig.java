package com.xzcode.ggcloud.eventbus.server.config;

import com.xzcode.ggcloud.eventbus.common.constant.EventbusConstant;
import com.xzcode.ggcloud.eventbus.server.subscription.SubscriptionManager;

import xzcode.ggserver.core.common.executor.DefaultTaskExecutor;
import xzcode.ggserver.core.common.executor.ITaskExecutor;
import xzcode.ggserver.core.server.IGGServer;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class EventbusServerConfig {
	
	//ggserver对象
	private IGGServer server;
	
	//订阅管理器
	private SubscriptionManager subscriptionManager = new SubscriptionManager();
	
	
	protected ITaskExecutor taskExecutor = new DefaultTaskExecutor("gg-evt-server-", 1);
	
	//服务端口
	private int port = 16384;
	
	//认证token
	private String authToken = EventbusConstant.DEFAULT_AUTH_TOKEN;
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public IGGServer getServer() {
		return server;
	}
	
	public void setServer(IGGServer server) {
		this.server = server;
	}
	
	public SubscriptionManager getSubscriptionManager() {
		return subscriptionManager;
	}

}
