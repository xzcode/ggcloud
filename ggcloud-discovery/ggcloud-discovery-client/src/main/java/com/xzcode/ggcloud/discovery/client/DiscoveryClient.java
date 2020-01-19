package com.xzcode.ggcloud.discovery.client;

import java.nio.charset.Charset;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.events.ConnActiveEventListener;
import com.xzcode.ggcloud.discovery.client.events.ConnCloseEventListener;
import com.xzcode.ggcloud.discovery.client.handler.RegisterRespHandler;
import com.xzcode.ggcloud.discovery.client.handler.ServiceListRespHandler;
import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;
import com.xzcode.ggcloud.discovery.common.message.resp.ServiceListResp;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;

public class DiscoveryClient {
	
	
	private DiscoveryClientConfig config;
	
	public DiscoveryClient(DiscoveryClientConfig config) {
		this.config = config;
	}

	public void start() {
		GGClientConfig ggConfig = new GGClientConfig();
		ggConfig.init();
		ggConfig.setWorkerGroupThreadFactory(new GGThreadFactory("discovery-W-", false));
		GGClient ggClient = new GGClient(ggConfig);
		RegistryInfo registry = config.getRegistryManager().getRandomRegistry();
		
		ggClient.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener());
		ggClient.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		
		ggClient.onMessage(RegisterResp.ACTION, new RegisterRespHandler(config));
		ggClient.onMessage(ServiceListResp.ACTION, new ServiceListRespHandler(config));
		
		ggClient.connect(registry.getDomain(), registry.getPort());
	}

}
