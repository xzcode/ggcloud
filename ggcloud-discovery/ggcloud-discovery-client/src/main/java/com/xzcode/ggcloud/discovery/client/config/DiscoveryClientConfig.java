package com.xzcode.ggcloud.discovery.client.config;

import java.util.ArrayList;
import java.util.List;

import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;
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
	protected GGClient ggclient;
	
	//服务管理器
	protected ServiceManager serviceManager;
	
	//注册中心信息
	protected List<RegistryInfo> registries = new ArrayList<>();
	
	//注册中心管理器
	protected RegistryManager registryManager = new RegistryManager(registries);
	
	//客户端汇报超时时间(秒)
	protected long clientReportInterval = 30L * 1000L;
	
	//重连间隔-秒
	protected long reconnectInterval = 5L * 1000L;
	
	/**
	 * 尝试重新注册周期，ms
	 */
	protected long tryRegisterInterval = 10L * 1000L;
	
	//所在地区
	protected String region = "default";
		
	//所在分区
	protected String zone = "default";

	public GGClient getGgclient() {
		return ggclient;
	}

	public void setGgclient(GGClient ggclient) {
		this.ggclient = ggclient;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public List<RegistryInfo> getRegistries() {
		return registries;
	}

	public void setRegistries(List<RegistryInfo> registries) {
		this.registries = registries;
	}

	public RegistryManager getRegistryManager() {
		return registryManager;
	}

	public void setRegistryManager(RegistryManager registryManager) {
		this.registryManager = registryManager;
	}

	public long getClientReportInterval() {
		return clientReportInterval;
	}

	public void setClientReportInterval(long clientReportInterval) {
		this.clientReportInterval = clientReportInterval;
	}

	public long getReconnectInterval() {
		return reconnectInterval;
	}

	public void setReconnectInterval(long reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
	}

	public long getTryRegisterInterval() {
		return tryRegisterInterval;
	}

	public void setTryRegisterInterval(long tryRegisterInterval) {
		this.tryRegisterInterval = tryRegisterInterval;
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
