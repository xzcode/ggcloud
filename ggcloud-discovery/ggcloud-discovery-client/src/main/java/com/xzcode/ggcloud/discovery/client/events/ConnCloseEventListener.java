package com.xzcode.ggcloud.discovery.client.events;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.service.ServiceManager;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;

public class ConnCloseEventListener implements IEventListener<Void>{
	
	private DiscoveryClientConfig config;
	
	public ConnCloseEventListener(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void onEvent(EventData<Void> eventData) {
		config.getRegistryManager().getRegistriedInfo().setActive(false);
		ServiceManager serviceManager = config.getServiceManager();
		serviceManager.clearAllServices();
		//断开连接，触发重连
		config.getDiscoveryClient().connect();
	}


	
}
