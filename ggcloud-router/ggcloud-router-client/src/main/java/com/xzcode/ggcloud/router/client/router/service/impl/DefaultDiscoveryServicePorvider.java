package com.xzcode.ggcloud.router.client.router.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xzcode.ggcloud.discovery.client.DiscoveryClient;
import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientService;
import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientServiceManager;
import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceProvider;
import com.xzcode.ggcloud.router.client.router.service.listener.IAddRouterServiceListener;
import com.xzcode.ggcloud.router.client.router.service.listener.IRemoveRouterServiceListener;
import com.xzcode.ggcloud.router.client.router.service.listener.IRouterServiceListener;

import xzcode.ggserver.core.common.message.Pack;

/**
 * 默认路由服务提供者
 * 
 * @author zai
 * 2019-11-07 17:36:10
 */
public class DefaultDiscoveryServicePorvider implements IRouterServiceProvider{
	
	/**
	 * 路由客户端配置
	 */
	protected RouterClientConfig config;
	
	/**
	 * 注册中心客户端
	 */
	protected DiscoveryClient discoveryClient;
	
	/**
	 * 服务管理器
	 */
	protected DiscoveryClientServiceManager serviceManager;
	
	/**
	 * 添加路由服务监听器
	 */
	protected List<IAddRouterServiceListener> addRouterServiceListeners = new ArrayList<>();
	
	/**
	 * 移除路由服务监听器
	 */
	protected List<IRemoveRouterServiceListener> removeRouterServiceListeners = new ArrayList<>();
	
	/**
	 * 服务集合<服务id,路由服务对象>
	 */
	protected Map<String, IRouterService> services = new ConcurrentHashMap<>();
	
	/**
	 * actionId对应路由服务缓存,<actionId,路由服务对象>
	 */
	protected Map<String, IRouterService> actionServiceCache = new ConcurrentHashMap<>();
	
	
	public DefaultDiscoveryServicePorvider(RouterClientConfig config, DiscoveryClient discoveryClient) {
		this.config = config;
		this.serviceManager = discoveryClient.getConfig().getServiceManager();
		//添加注册中心服务管器的服务注册监听器
		this.serviceManager.addRegisterListener(service -> {
			//优先移除旧服务
			removeService(service.getServiceId());
			//注册路由服务
			registerRouterService(service);
		});
		//添加注册中心服务管器的服务取消注册监听器
		this.serviceManager.addUnregisterListener(service -> {
			removeService(service.getServiceId());
		});
		//添加注册中心服务管器的服务更新监听器
		this.serviceManager.addUpdateListener(service -> {
			IRouterService routerService = getService(service.getServiceId());
			if (routerService == null) {
				registerRouterService(service);
				return;
			}
		});
	}
	
	/**
	 * 注册路由服务
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-06 18:24:17
	 */
	private void registerRouterService(DiscoveryClientService service) {
		//创建新服务对象
		DefaultRouterService routerService = new DefaultRouterService(config, service.getServiceId());
        routerService.setHost(service.getHost());
        routerService.setPort(service.getPort());
        routerService.setServiceId(service.getServiceId());
        routerService.setServcieName(service.getServiceName());
        routerService.addAllExtraData(service.getExtraData());
        routerService.setServiceMatcher(new DefaultDiscoveryRouterServiceActionPrefixMatcher(service));
        addService(routerService);
        routerService.init();
	}

	@Override
	public IRouterService getService(String serviceId) {
		return services.get(serviceId);
	}

	@Override
	public IRouterService addService(IRouterService service) {
		return services.put(service.getServiceId(), service);
	}

	@Override
	public IRouterService removeService(String serviceId) {
		IRouterService service = services.remove(serviceId);
		if (service != null) {
			service.shutdown();
		}
		return service;
	}

	@Override
	public IRouterService matchService(Pack pack) {
		String actionId = pack.getActionString(config.getCharset());
		//尝试从缓存中获取服务
		IRouterService service = actionServiceCache.get(actionId);
		if (service != null) {
			return service;
		}
		//遍历进行服务匹配
		for (Entry<String, IRouterService> entry : services.entrySet()) {
			service = entry.getValue();
			if (service.getServiceMatcher().match(pack)) {
				actionServiceCache.put(actionId, service);
				return service;
			}
		}
		return null;
	}


	@Override
	public void addListener(IRouterServiceListener listener) {
		if (listener instanceof IAddRouterServiceListener) {
			addRouterServiceListeners.add((IAddRouterServiceListener) listener);
			return;
		}
		if (listener instanceof IRemoveRouterServiceListener) {
			removeRouterServiceListeners.add((IRemoveRouterServiceListener) listener);
			return;
		}
	}

	@Override
	public void removeListener(IRouterServiceListener listener) {
		if (listener instanceof IAddRouterServiceListener) {
			addRouterServiceListeners.remove((IAddRouterServiceListener) listener);
			return;
		}
		if (listener instanceof IRemoveRouterServiceListener) {
			removeRouterServiceListeners.remove((IRemoveRouterServiceListener) listener);
			return;
		}
	}

	
}
