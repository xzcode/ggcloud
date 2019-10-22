package com.xzcode.ggcloud.discovery.client;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.xzcode.ggcloud.discovery.client.config.GGCDiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.events.ConnActiveEventHandler;
import com.xzcode.ggcloud.discovery.client.events.ConnCloseEventHandler;
import com.xzcode.ggcloud.discovery.client.handler.RegisterRespHandler;
import com.xzcode.ggcloud.discovery.client.handler.ServiceListRespHandler;
import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;
import com.xzcode.ggcloud.discovery.common.message.resp.ServiceListResp;

import nonapi.io.github.classgraph.concurrency.SimpleThreadFactory;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;

public class GGCDiscoveryClient {
	
	
	/**
	 * 线程池执行器
	 */
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new SimpleThreadFactory("Registry-Manager-", false));
	
	/**
	 * 尝试重新注册周期，ms
	 */
	private long tryRegisterInterval = 10 * 1000;
	
	private GGCDiscoveryClientConfig config;
	
	
	
	public GGCDiscoveryClient(GGCDiscoveryClientConfig config) {
		this.config = config;
	}



	public void start() {
		GGClientConfig ggConfig = new GGClientConfig();
		ggConfig.init();
		ggConfig.setWorkerGroupThreadFactory(new SimpleThreadFactory("discovery-W-", false));
		GGClient ggClient = new GGClient(ggConfig);
		RegistryInfo registry = config.getRegistryManager().getRandomRegistry();
		
		ggClient.onEvent(GGEvents.Connection.OPEN, new ConnActiveEventHandler());
		ggClient.onEvent(GGEvents.Connection.CLOSE, new ConnCloseEventHandler(config));
		
		ggClient.onMessage(RegisterResp.ACTION, new RegisterRespHandler(config));
		ggClient.onMessage(ServiceListResp.ACTION, new ServiceListRespHandler(config));
		
		ggClient.connect(registry.getDomain(), registry.getPort());
	}

}
