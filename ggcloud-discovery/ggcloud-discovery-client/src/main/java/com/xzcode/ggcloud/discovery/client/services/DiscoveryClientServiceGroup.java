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
	
	private Map<String, DiscoveryClientServiceInfo> serviceMap = new ConcurrentHashMap<>();
	
	
	public void addServiceInfo(DiscoveryClientServiceInfo discoveryClientServiceInfo) {
		serviceMap.putIfAbsent(discoveryClientServiceInfo.getServiceId(), discoveryClientServiceInfo);
	}
	
	public void removeServiceInfo(String serviceId) {
		serviceMap.remove(serviceId);
	}
	
	public DiscoveryClientServiceInfo getServiceInfo(String serviceId) {
		return serviceMap.get(serviceId);
	}
	
}
