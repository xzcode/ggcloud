package com.xzcode.ggcloud.discovery.client.config;

import com.xzcode.ggcloud.discovery.client.registry.RegistryManager;
import com.xzcode.ggcloud.discovery.client.services.ServiceManager;

import xzcode.ggserver.core.client.GGClient;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class DiscoveryClientConfig {
	
	//GGClient对象
	private GGClient ggClient;
	
	//服务管理器
	private ServiceManager serviceManager;
	
	//注册中心管理器
	private RegistryManager registryManager = new RegistryManager();
	
	//客户端汇报超时时间(秒)
	private long clientReportTimeout = 30L;
	
	//重连次数
	private int reconnectTimes = 3;
	
	//重连间隔-秒
	private int reconnectInterval = 5;
	
	//所在地区
	private String region = "default";
		
	//所在分区
	private String zone = "default";
	
	

	public int getReconnectTimes() {
		return reconnectTimes;
	}

	public void setReconnectTimes(int reconnectTimes) {
		this.reconnectTimes = reconnectTimes;
	}

	public int getReconnectInterval() {
		return reconnectInterval;
	}

	public void setReconnectInterval(int reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
	}

	public long getClientReportTimeout() {
		return clientReportTimeout;
	}

	public void setClientReportTimeout(long clientReportTimeout) {
		this.clientReportTimeout = clientReportTimeout;
	}
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public RegistryManager getRegistryManager() {
		return registryManager;
	}

	public void setRegistryManager(RegistryManager registryManager) {
		this.registryManager = registryManager;
	}

	public GGClient getGgClient() {
		return ggClient;
	}

	public void setGgClient(GGClient ggClient) {
		this.ggClient = ggClient;
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
