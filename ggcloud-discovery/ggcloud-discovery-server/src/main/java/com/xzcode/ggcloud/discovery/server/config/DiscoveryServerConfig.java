package com.xzcode.ggcloud.discovery.server.config;

import com.xzcode.ggcloud.discovery.common.services.ServiceManager;

import xzcode.ggserver.core.server.IGGServer;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class DiscoveryServerConfig {
	
	//ggserver对象
	private IGGServer server;
	
	//服务管理器
	private ServiceManager serviceManager = new ServiceManager();
	
	//服务端口
	private int port = 19394;
	
	//认证token
	private String authToken;
	
	//客户端汇报超时时间(秒)
	private long clientReportTimeout = 30L;
	
	//所在地区
	private String region = "default";
	
	//所在分区
	private String zone = "default";

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
	
	public IGGServer getServer() {
		return server;
	}
	
	public void setServer(IGGServer server) {
		this.server = server;
	}
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	
	

}
