package com.xzcode.ggcloud.discovery.server.config;

import com.xzcode.ggcloud.discovery.server.services.ServiceManager;

import xzcode.ggserver.core.GGServer;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class GGCDiscoveryServerConfig {
	
	//ggserver对象
	private GGServer ggServer;
	
	//服务管理器
	private ServiceManager serviceManager = new ServiceManager();
	
	//服务端口
	private int port = 9394;
	
	//认证token
	private String authToken;
	
	//客户端汇报超时时间(秒)
	private long clientReportTimeout = 30L;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public long getClientReportTimeout() {
		return clientReportTimeout;
	}

	public void setClientReportTimeout(long clientReportTimeout) {
		this.clientReportTimeout = clientReportTimeout;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public GGServer getGgServer() {
		return ggServer;
	}
	
	public void setGgServer(GGServer ggServer) {
		this.ggServer = ggServer;
	}
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

}
