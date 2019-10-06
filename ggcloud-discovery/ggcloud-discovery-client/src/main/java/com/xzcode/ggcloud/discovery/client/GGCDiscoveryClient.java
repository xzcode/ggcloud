package com.xzcode.ggcloud.discovery.client;

import com.xzcode.ggcloud.discovery.client.config.GGCDiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.events.ConnActiveEventHandler;
import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;

import nonapi.io.github.classgraph.concurrency.SimpleThreadFactory;
import xzcode.ggserver.core.GGClient;
import xzcode.ggserver.core.config.GGConfig;
import xzcode.ggserver.core.event.GGEvents;

public class GGCDiscoveryClient {
	
	private GGCDiscoveryClientConfig config;
	
	
	
	public GGCDiscoveryClient(GGCDiscoveryClientConfig config) {
		this.config = config;
	}



	public void start() {
		GGConfig ggConfig = new GGConfig();
		ggConfig.setWorkerGroupThreadFactory(new SimpleThreadFactory("discovery-W-", false));
		GGClient ggClient = new GGClient(ggConfig);
		RegistryInfo registry = config.getRegistryManager().getRandomRegistry();
		ggClient.connect(registry.getDomain(), registry.getPort());
		ggClient.onEvent(GGEvents.ConnectionState.ACTIVE, new ConnActiveEventHandler());
	}

}
