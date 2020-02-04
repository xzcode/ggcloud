package com.xzcode.ggcloud.discovery.server.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceDownResp;

import xzcode.ggserver.core.common.executor.ITaskExecutor;
import xzcode.ggserver.core.common.executor.SingleThreadTaskExecutor;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 服务管理器
 * 
 * @author zai
 * 2020-02-03 16:26:45
 */
public class ServiceManager {
	
	//服务组集合
	private Map<String, ServiceGroup> serviceGroups = new ConcurrentHashMap<>();
	
	//任务执行器
	private ITaskExecutor taskExecutor = new SingleThreadTaskExecutor();
	
	//检查服务周期
	private static final long CHECK_SERVICE_INTERVAL = 5L * 1000L;
	
	
	public ServiceManager() {
		startCheckServiceTask();
	}
	
	
	/**
	 * 注册服务
	 * 
	 * @param serviceInfo
	 * @author zai
	 * 2020-02-03 16:27:16
	 */
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
	/**
	 * 开启检查任务
	 * 
	 * @author zai
	 * 2020-02-03 16:29:09
	 */
	public void startCheckServiceTask() {
		taskExecutor.schedule(CHECK_SERVICE_INTERVAL, () -> {
			try {
				checkService();
			} catch (Exception e) {
				GGLoggerUtil.getLogger(this).error("Check Service Task Error!", e);
			}
		});
	}
	/**
	 * 检查服务可用性
	 * 
	 * @author zai
	 * 2020-02-03 16:29:09
	 */
	public void checkService() {
		
		List<ServiceInfo> downServices = new ArrayList<>();
		List<ServiceInfo> availableServices = new ArrayList<>();
		
		List<ServiceInfo> serviceList = getServiceList();
		
		for (ServiceInfo service : serviceList) {
			if (service.isTimeout()) {
				downServices.add(service);
				continue;
			}
			availableServices.add(service);
		}
		
		//处理超时服务
		for (ServiceInfo downService : downServices) {
			//通知其他服务，某些服务已经超时
			for (ServiceInfo availableService : availableServices) {
				GGSession session = availableService.getSession();
				DiscoveryServiceDownResp resp = new DiscoveryServiceDownResp();
				resp.setServiceName(downService.getServiceName());
				resp.setServiceId(downService.getServiceId());
				session.send(resp);
			}
		}
	}
	
	/**
	 * 获取服务列表
	 * 
	 * @return
	 * @author zai
	 * 2020-02-04 18:53:42
	 */
	public List<ServiceInfo> getServiceList() {
		List<ServiceInfo> list = new ArrayList<>();
		
		for (Entry<String, ServiceGroup> groupEntry : serviceGroups.entrySet()) {
			
			ServiceGroup serviceGroup = groupEntry.getValue();
			Map<String, ServiceInfo> services = serviceGroup.getServices();
			Iterator<Entry<String, ServiceInfo>> serviceIterator = services.entrySet().iterator();
			
			while (serviceIterator.hasNext()) {
				ServiceInfo serviceInfo = (ServiceInfo) serviceIterator.next();
				list.add(serviceInfo);
			}
		}
		
		return list;
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
