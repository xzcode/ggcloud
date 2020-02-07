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
	
	private Map<String, DiscoveryClientService> serviceMap = new ConcurrentHashMap<>();
	
	
	public void addServiceInfo(DiscoveryClientService discoveryClientService) {
		serviceMap.putIfAbsent(discoveryClientService.getServiceId(), discoveryClientService);
	}
	
	public DiscoveryClientService removeServiceInfo(String serviceId) {
		return serviceMap.remove(serviceId);
	}
	
	public DiscoveryClientService getServiceInfo(String serviceId) {
		return serviceMap.get(serviceId);
	}
	
}
