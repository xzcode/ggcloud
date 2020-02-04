package com.xzcode.ggcloud.discovery.client.services;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理器
 * 
 * @author zai
 * 2020-02-04 14:33:19
 */
public class DiscoveryClientServiceManager {
	
	/**
	 * 服务组集合
	 */
	private Map<String, DiscoveryClientServiceGroup> serviceGroups = new ConcurrentHashMap<>();
	
	/**
	 * 注册服务
	 * 
	 * @param serviceInfo
	 * @author zai
	 * 2020-02-04 14:33:41
	 */
	public void registerService(DiscoveryClientServiceInfo serviceInfo) {
		DiscoveryClientServiceGroup group = serviceGroups.get(serviceInfo.getServiceName());
		if (group == null) {
			synchronized (serviceGroups) {
				group = serviceGroups.get(serviceInfo.getServiceName());
				if (group == null) {
					group = new DiscoveryClientServiceGroup();
					serviceGroups.put(serviceInfo.getServiceName(), group);
				}
				group.addServiceInfo(serviceInfo);
			}
		}
	}
	
	/**
	 * 移除服务
	 * 
	 * @param discoveryClientServiceInfo
	 * @author zai
	 * 2020-02-04 14:33:48
	 */
	public void removeService(DiscoveryClientServiceInfo discoveryClientServiceInfo) {
		DiscoveryClientServiceGroup groups = serviceGroups.get(discoveryClientServiceInfo.getServiceName());
		if (groups != null) {
			groups.removeServiceInfo(discoveryClientServiceInfo.getServiceId());
		}
	}
	
	/**
	 * 获取服务组
	 * 
	 * @param serviceName
	 * @return
	 * @author zai
	 * 2020-02-04 14:33:57
	 */
	public DiscoveryClientServiceGroup getServiceGroup(String serviceName) {
		return serviceGroups.get(serviceName);
	}
	
	/**
	 * 获取服务
	 * 
	 * @param serviceId
	 * @return
	 * @author zai
	 * 2020-02-04 14:34:10
	 */
	public DiscoveryClientServiceInfo getService(String serviceId) {
		for (Entry<String, DiscoveryClientServiceGroup> entry : serviceGroups.entrySet()) {
			DiscoveryClientServiceGroup group = entry.getValue();
			DiscoveryClientServiceInfo serviceInfo = group.getServiceInfo(serviceId);
			if (serviceInfo != null) {
				return serviceInfo;
			}
		}
		return null;
	}
	
}
