package com.xzcode.ggcloud.detector.server.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggserver.core.session.GGSession;

public class GGCDetectorServiceManager {
	
	private Map<GGSession, GGCDetectorServiceInfo> sessionServiceBindMap = new ConcurrentHashMap<>();
	private Map<String, List<GGCDetectorServiceInfo>> serviceGroup = new ConcurrentHashMap<>();
	
	public void addServiceInfo(GGCDetectorServiceInfo serviceInfo) {
		sessionServiceBindMap.put(serviceInfo.getSession(), serviceInfo);
		List<GGCDetectorServiceInfo> list = serviceGroup.get(serviceInfo.getServiceName());
		if (list == null) {
			synchronized (serviceGroup) {
				if (list == null) {
					list = new ArrayList<>(5);
					serviceGroup.put(serviceInfo.getServiceName(), list);
				}
			}
		}
		list.add(serviceInfo);
	}
	
	public void removeServiceInfo(GGSession session) {
		GGCDetectorServiceInfo serviceInfo = sessionServiceBindMap.remove(session);
		List<GGCDetectorServiceInfo> list = serviceGroup.get(serviceInfo.getServiceName());
		if (list != null) {
			list.remove(serviceInfo);
		}
	}
	
	public GGCDetectorServiceInfo getServiceInfo(GGSession session) {
		return sessionServiceBindMap.get(session);
	}
	
}
