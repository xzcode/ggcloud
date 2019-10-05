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
public class ServiceGroup {
	
	private Map<String, ServiceInfo> serviceMap = new ConcurrentHashMap<>();
	
	
	public void addServiceInfo(ServiceInfo serviceInfo) {
		serviceMap.putIfAbsent(serviceInfo.getServiceId(), serviceInfo);
	}
	
	public void removeServiceInfo(String serviceId) {
		serviceMap.remove(serviceId);
	}
	
	public ServiceInfo getServiceInfo(String serviceId) {
		return serviceMap.get(serviceId);
	}
	
}
