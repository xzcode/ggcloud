package com.xzcode.ggcloud.session.group.server.config;

import com.xzcode.ggcloud.session.group.common.constant.GGSesssionGroupConstant;
import com.xzcode.ggcloud.session.group.server.subscription.SubscriptionManager;

import xzcode.ggserver.core.server.IGGServer;

/**
 * 总配置
 *
 * @author zai
 * 2020-04-08 11:53:31
 */
public class SessionGroupServerConfig {
	
	//sessionServer对象
	private IGGServer sessionServer;
	
	//是否打印pingpong包信息
	protected boolean printPingPongInfo = false;
	
	//订阅管理器
	private SubscriptionManager subscriptionManager = new SubscriptionManager();
	
	//服务端口
	private int port = GGSesssionGroupConstant.DEFAULT_SERVER_PORT;
	
	//认证token
	private String authToken = GGSesssionGroupConstant.DEFAULT_AUTH_TOKEN;
	
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
	
	public IGGServer getSessionServer() {
		return sessionServer;
	}
	
	public void setSessionServer(IGGServer sessionServer) {
		this.sessionServer = sessionServer;
	}
	
	public SubscriptionManager getSubscriptionManager() {
		return subscriptionManager;
	}

	public boolean isPrintPingPongInfo() {
		return printPingPongInfo;
	}
	
	public void setPrintPingPongInfo(boolean printPingPongInfo) {
		this.printPingPongInfo = printPingPongInfo;
	}
	

}
