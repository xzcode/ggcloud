package com.xzcode.ggcloud.eventbus.client.config;

import com.xzcode.ggcloud.eventbus.client.EventbusClient;
import com.xzcode.ggcloud.eventbus.client.subscription.EventSubscribeManager;
import com.xzcode.ggcloud.eventbus.common.constant.EventbusConstant;

import xzcode.ggserver.core.client.GGClient;

/**
 * 配置
 * 
 * 
 * @author zai 2019-10-04 17:23:47
 */
public class EventbusClientConfig {

	// discoveryClient对象
	protected EventbusClient eventbusClient;

	//是否输出底层ping pong信息
	protected boolean printPingPongInfo = false;

	// GGClient对象
	protected GGClient ggclient;

	// 工作线程数
	protected int workThreadSize = 8;
	
	// 连接数
	protected int connectionSize = 8;

	// 是否打印pingpong包信息

	// 注册中心管理器
	protected EventSubscribeManager registryManager = new EventSubscribeManager();

	// 验证token
	protected String authToken = EventbusConstant.DEFAULT_AUTH_TOKEN;

	public EventbusClient getEventbusClient() {
		return eventbusClient;
	}

	public void setEventbusClient(EventbusClient eventbusClient) {
		this.eventbusClient = eventbusClient;
	}

	public boolean isPrintPingPongInfo() {
		return printPingPongInfo;
	}

	public void setPrintPingPongInfo(boolean printPingPongInfo) {
		this.printPingPongInfo = printPingPongInfo;
	}

	public GGClient getGgclient() {
		return ggclient;
	}

	public void setGgclient(GGClient ggclient) {
		this.ggclient = ggclient;
	}

	public int getWorkThreadSize() {
		return workThreadSize;
	}

	public void setWorkThreadSize(int workThreadSize) {
		this.workThreadSize = workThreadSize;
	}

	public EventSubscribeManager getRegistryManager() {
		return registryManager;
	}

	public void setRegistryManager(EventSubscribeManager registryManager) {
		this.registryManager = registryManager;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public int getConnectionSize() {
		return connectionSize;
	}
	
	public void setConnectionSize(int connectionSize) {
		this.connectionSize = connectionSize;
	}
	
	

}
