package com.xzcode.ggcloud.discovery.client.config;

import com.xzcode.ggcloud.discovery.client.registry.RegistryManager;
import com.xzcode.ggcloud.discovery.client.services.ServiceManager;

import xzcode.ggserver.core.GGClient;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class GGCDiscoveryClientConfig {
	
	//GGClient对象
	private GGClient ggClient;
	
	//服务管理器
	private ServiceManager serviceManager;
	
	//服务管理器
	private RegistryManager registryManager = new RegistryManager();
	
	//客户端汇报超时时间(秒)
	private long clientReportTimeout = 30L;

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
	
	

}
