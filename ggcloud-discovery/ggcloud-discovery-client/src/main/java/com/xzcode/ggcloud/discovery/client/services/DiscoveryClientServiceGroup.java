package com.xzcode.ggcloud.discovery.client.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务信息组
 * 
 * 
 * @author zai
 * 2019-10-04 16:14:45
 */
public class DiscoveryClientServiceGroup {
	
	private Map<String, DiscoveryClientService> services = new ConcurrentHashMap<>();
	
	
	public void addServiceInfo(DiscoveryClientService discoveryClientService) {
		services.putIfAbsent(discoveryClientService.getServiceId(), discoveryClientService);
	}
	
	public DiscoveryClientService removeServiceInfo(String serviceId) {
		return services.remove(serviceId);
	}
	
	public DiscoveryClientService getServiceInfo(String serviceId) {
		return services.get(serviceId);
	}
	
	public Map<String, DiscoveryClientService> getServices() {
		return services;
	}
	
}
