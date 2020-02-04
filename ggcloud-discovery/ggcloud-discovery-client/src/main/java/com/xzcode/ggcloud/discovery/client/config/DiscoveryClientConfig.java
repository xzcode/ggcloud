package com.xzcode.ggcloud.discovery.client.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;
import com.xzcode.ggcloud.discovery.client.registry.RegistryManager;
import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientServiceManager;
import com.xzcode.ggcloud.discovery.common.constant.DiscoveryConstant;
import com.xzcode.ggcloud.discovery.common.util.DiscoveryServiceIdUtil;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.common.session.GGSession;

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
	
	//GGSession对象
	protected GGSession session;
	
	//服务管理器
	protected DiscoveryClientServiceManager discoveryClientServiceManager;
	
	//注册中心信息
	protected List<RegistryInfo> registries = new ArrayList<>();
	
	
	//注册中心管理器
	protected RegistryManager registryManager = new RegistryManager(registries);
	
	//客户端汇报超时时间(秒)
	protected long clientReportInterval = 30L * 1000L;
	
	//重连间隔-秒
	protected long reconnectInterval = 5L * 1000L;
	
	//尝试重新注册周期，ms
	protected long tryRegisterInterval = 10L * 1000L;
	
	//验证token
	protected String authToken = DiscoveryConstant.DEFAULT_AUTH_TOKEN;

	//服务id
	protected String serviceId = DiscoveryServiceIdUtil.newServiceId();
	
	//服务名称
	protected String serviceName = "default_service";
	
	//所在地区
	protected String region = "default";
		
	//所在分区
	protected String zone = "default";
	

	/**
	 * 额外数据
	 */
	private Map<String, String> extraData;
	
	/**
	 * 添加额外参数
	 * 
	 * @param key
	 * @param value
	 * @author zai
	 * 2020-02-04 11:19:05
	 */
	public void addExtraData(String key, String value) {
		if (extraData == null) {
			extraData = new LinkedHashMap<>();
		}
		extraData.put(key, value);
	}
	
	public Map<String, String> getExtraData() {
		return extraData;
	}
	
	public void setExtraData(Map<String, String> extraData) {
		this.extraData = extraData;
	}

	public GGClient getGGclient() {
		return ggclient;
	}

	public void setGGclient(GGClient ggclient) {
		this.ggclient = ggclient;
	}

	public DiscoveryClientServiceManager getServiceManager() {
		return discoveryClientServiceManager;
	}

	public void setServiceManager(DiscoveryClientServiceManager discoveryClientServiceManager) {
		this.discoveryClientServiceManager = discoveryClientServiceManager;
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
	
	public String getAuthToken() {
		return authToken;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public void setSession(GGSession session) {
		this.session = session;
	}
	public GGSession getSession() {
		return session;
	}

}
