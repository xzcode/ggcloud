package com.xzcode.ggcloud.discovery.client.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggserver.core.common.session.GGSession;

public class ServiceManager {
	
	private Map<String, ServiceGroup> serviceGroups = new ConcurrentHashMap<>();
	
	
	public void registerService(ServiceInfo serviceInfo) {
		ServiceGroup group = serviceGroups.get(serviceInfo.getServiceName());
		if (group == null) {
			synchronized (serviceGroups) {
				group = serviceGroups.get(serviceInfo.getServiceName());
				if (group == null) {
					group = new ServiceGroup();
					group.addServiceInfo(serviceInfo);
					serviceGroups.put(serviceInfo.getServiceName(), group);
				}
			}
		}
	}
	
	public void removeServiceInfo(ServiceInfo serviceInfo) {
		ServiceGroup groups = serviceGroups.get(serviceInfo.getServiceName());
		if (groups != null) {
			groups.removeServiceInfo(serviceInfo.getServiceId());
		}
	}
	
	public ServiceInfo getServiceInfo(ServiceInfo serviceInfo) {
		ServiceGroup groups = serviceGroups.get(serviceInfo.getServiceName());
		if (groups != null) {
			return groups.getServiceInfo(serviceInfo.getServiceId());
		}
		return null;
	}
	
}
