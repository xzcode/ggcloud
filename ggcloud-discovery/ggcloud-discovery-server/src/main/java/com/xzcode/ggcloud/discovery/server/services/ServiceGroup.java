package com.xzcode.ggcloud.discovery.server.services;

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
	
	/**
	 * 服务名称
	 */
	private String serviceName;
	
	/**
	 * 服务集合<服务id， 服务信息>
	 */
	private Map<String, ServiceInfo> services = new ConcurrentHashMap<>();
	
	
	public void addServiceInfo(ServiceInfo serviceInfo) {
		services.putIfAbsent(serviceInfo.getServiceId(), serviceInfo);
	}
	
	public void removeServiceInfo(String serviceId) {
		services.remove(serviceId);
	}
	
	public ServiceInfo getServiceInfo(String serviceId) {
		return services.get(serviceId);
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public Map<String, ServiceInfo> getServices() {
		return services;
	}
	
}
