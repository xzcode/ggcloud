package com.xzcode.ggcloud.discovery.client.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xzcode.ggcloud.discovery.client.services.listener.IDiscoveryClientRegisterServiceListener;
import com.xzcode.ggcloud.discovery.client.services.listener.IDiscoveryClientUnregisterServiceListener;
import com.xzcode.ggcloud.discovery.client.services.listener.IDiscoveryClientUpdateServiceListener;

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
	 * 注册监听器
	 */
	private List<IDiscoveryClientRegisterServiceListener> registerListeners = new ArrayList<>(5);
	
	/**
	 * 取消注册监听器
	 */
	private List<IDiscoveryClientUnregisterServiceListener> unregisterListeners = new ArrayList<>(5);
	
	/**
	 * 服务更新监听器
	 */
	private List<IDiscoveryClientUpdateServiceListener> updateListeners = new ArrayList<>(5);
	
	
	/**
	 * 添加注册监听器
	 * 
	 * @param listener
	 * @author zai
	 * 2020-02-06 15:15:56
	 */
	public void addRegisterListener(IDiscoveryClientRegisterServiceListener listener) {
		this.registerListeners.add(listener);
	}
	
	/**
	 * 添加取消注册监听器
	 * 
	 * @param listener
	 * @author zai
	 * 2020-02-06 15:16:06
	 */
	public void addUnregisterListener(IDiscoveryClientUnregisterServiceListener listener) {
		this.unregisterListeners.add(listener);
	}
	
	/**
	 * 添加更新服务监听器
	 * 
	 * @param listener
	 * @author zai
	 * 2020-02-06 15:16:06
	 */
	public void addUpdateListener(IDiscoveryClientUpdateServiceListener listener) {
		this.updateListeners.add(listener);
	}
	
	/**
	 * 注册服务
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-04 14:33:41
	 */
	public void registerService(DiscoveryClientService service) {
		DiscoveryClientServiceGroup group = serviceGroups.get(service.getServiceName());
		if (group == null) {
			synchronized (serviceGroups) {
				group = serviceGroups.get(service.getServiceName());
				if (group == null) {
					group = new DiscoveryClientServiceGroup();
					serviceGroups.put(service.getServiceName(), group);
				}
				group.addServiceInfo(service);
				if (this.registerListeners != null) {
					for (IDiscoveryClientRegisterServiceListener listener : registerListeners) {
						listener.onRegister(service);						
					}
				}
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
	public void removeService(DiscoveryClientService service) {
		DiscoveryClientServiceGroup groups = serviceGroups.get(service.getServiceName());
		if (groups != null) {
			groups.removeServiceInfo(service.getServiceId());
			if (this.unregisterListeners != null) {
				for (IDiscoveryClientUnregisterServiceListener listener : unregisterListeners) {
					listener.onUnregister(service);						
				}
			}
		}
	}
	
	/**
	 * 更新服务
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-06 17:22:06
	 */
	public void updateService(DiscoveryClientService service) {
		DiscoveryClientServiceGroup groups = serviceGroups.get(service.getServiceName());
		if (groups != null) {
			DiscoveryClientService oldService = groups.getServiceInfo(service.getServiceId());
			if (oldService != null) {
				oldService.setExtraData(service.getExtraData());
				
				if (this.updateListeners != null) {
					for (IDiscoveryClientUpdateServiceListener listener : updateListeners) {
						listener.onUpdate(service);						
					}
				}
			}
		}
	}
	
	/**
	 * 移除服务
	 * 
	 * @param serviceName
	 * @param serviceId
	 * @author zai
	 * 2020-02-06 15:01:25
	 */
	public void removeService(String serviceName, String serviceId) {
		DiscoveryClientServiceGroup groups = serviceGroups.get(serviceName);
		if (groups != null) {
			DiscoveryClientService service = groups.removeServiceInfo(serviceId);
			if (service == null) {
				return;
			}
			if (this.unregisterListeners != null) {
				for (IDiscoveryClientUnregisterServiceListener listener : unregisterListeners) {
					listener.onUnregister(service);						
				}
			}
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
	public DiscoveryClientService getService(String serviceId) {
		for (Entry<String, DiscoveryClientServiceGroup> entry : serviceGroups.entrySet()) {
			DiscoveryClientServiceGroup group = entry.getValue();
			DiscoveryClientService service = group.getServiceInfo(serviceId);
			if (service != null) {
				return service;
			}
		}
		return null;
	}
	
	/**
	 * 获取服务列表
	 * 
	 * @return
	 * @author zai
	 * 2020-02-04 18:53:42
	 */
	public List<DiscoveryClientService> getServiceList() {
		List<DiscoveryClientService> list = new ArrayList<>();
		
		for (Entry<String, DiscoveryClientServiceGroup> groupEntry : serviceGroups.entrySet()) {
			
			DiscoveryClientServiceGroup serviceGroup = groupEntry.getValue();
			Map<String, DiscoveryClientService> services = serviceGroup.getServices();
			Iterator<Entry<String, DiscoveryClientService>> serviceIterator = services.entrySet().iterator();
			
			while (serviceIterator.hasNext()) {
				DiscoveryClientService serviceInfo = (DiscoveryClientService) serviceIterator.next().getValue();
				list.add(serviceInfo);
			}
		}
		return list;
	}
	
}
