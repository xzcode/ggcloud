package com.xzcode.ggcloud.eventbus.server.config;

import com.xzcode.ggcloud.eventbus.common.constant.EventbusConstant;
import com.xzcode.ggcloud.eventbus.server.EventbusServer;
import com.xzcode.ggcloud.eventbus.server.subscription.SubscriptionManager;
import com.xzcode.ggcloud.session.group.server.SessionGroupServer;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class EventbusServerConfig {
	
	//eventbusServer对象
	protected EventbusServer eventbusServer;
	
	//sessionGroupServer对象
	protected SessionGroupServer sessionGroupServer;
	
	//订阅管理器
	protected SubscriptionManager subscriptionManager = new SubscriptionManager(this);
	
	//是否输出底层ping pong信息
	protected boolean printPingPongInfo = false;

	// 工作线程数
	protected int workThreadSize = 8;
	
	// 连接数
	protected int connectionSize = 8;
	
	//服务端口
	private int port = EventbusConstant.DEFAULT_SERVER_PORT;
	
	//认证token
	private String authToken = EventbusConstant.DEFAULT_AUTH_TOKEN;
	
	//是否输出包信息
	protected boolean printEventbusPackLog = false;
	
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
	
	public EventbusServer getEventbusServer() {
		return eventbusServer;
	}
	
	public void setEventbusServer(EventbusServer eventbusServer) {
		this.eventbusServer = eventbusServer;
	}
	
	public SubscriptionManager getSubscriptionManager() {
		return subscriptionManager;
	}
	
	
	public SessionGroupServer getSessionGroupServer() {
		return sessionGroupServer;
	}
	
	public void setSessionGroupServer(SessionGroupServer sessionGroupServer) {
		this.sessionGroupServer = sessionGroupServer;
	}

	public boolean isPrintPingPongInfo() {
		return printPingPongInfo;
	}

	public void setPrintPingPongInfo(boolean printPingPongInfo) {
		this.printPingPongInfo = printPingPongInfo;
	}

	public int getWorkThreadSize() {
		return workThreadSize;
	}

	public void setWorkThreadSize(int workThreadSize) {
		this.workThreadSize = workThreadSize;
	}

	public int getConnectionSize() {
		return connectionSize;
	}

	public void setConnectionSize(int connectionSize) {
		this.connectionSize = connectionSize;
	}

	public void setSubscriptionManager(SubscriptionManager subscriptionManager) {
		this.subscriptionManager = subscriptionManager;
	}

	public boolean isPrintEventbusPackLog() {
		return printEventbusPackLog;
	}

	public void setPrintEventbusPackLog(boolean printEventbusPackLog) {
		this.printEventbusPackLog = printEventbusPackLog;
	}
	
	
	
}
