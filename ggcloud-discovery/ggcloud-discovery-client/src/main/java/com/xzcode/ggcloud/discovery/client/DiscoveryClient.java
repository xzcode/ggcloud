package com.xzcode.ggcloud.discovery.client;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.events.ConnActiveEventListener;
import com.xzcode.ggcloud.discovery.client.events.ConnCloseEventListener;
import com.xzcode.ggcloud.discovery.client.handler.RegisterRespHandler;
import com.xzcode.ggcloud.discovery.client.handler.ServiceListRespHandler;
import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;
import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryRegisterReq;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryRegisterResp;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceListResp;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

public class DiscoveryClient {
	
	
	private DiscoveryClientConfig config;
	
	public DiscoveryClient(DiscoveryClientConfig config) {
		this.config = config;
	}

	public void start() {
		GGClientConfig ggConfig = new GGClientConfig();
		ggConfig.setWorkThreadSize(1);
		ggConfig.setProtocolType(ProtocolTypeConstants.TCP);
		ggConfig.setWorkerGroupThreadFactory(new GGThreadFactory("discovery-worker-", false));
		ggConfig.init();
		
		GGClient ggClient = new GGClient(ggConfig);
		config.setGGclient(ggClient);
		
		
		
		ggClient.addEventListener(GGEvents.Connection.OPENED, new ConnActiveEventListener());
		ggClient.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		
		ggClient.onMessage(DiscoveryRegisterResp.ACTION, new RegisterRespHandler(config));
		ggClient.onMessage(DiscoveryServiceListResp.ACTION, new ServiceListRespHandler(config));
		
		ggClient.addEventListener(GGEvents.Connection.CLOSED, e -> {
			System.out.println("conn closed: + "+ e.getChannel());
			connect();
		});
		ggClient.addEventListener(GGEvents.Connection.OPENED, e -> {
			System.out.println("conn opened: + "+ e.getChannel());
			//发送注册请求
			GGSession session = e.getSession();
			DiscoveryRegisterReq req = new DiscoveryRegisterReq();
			req.setServiceName("xxxxx");
			session.send(req);
		});
		
		connect();
		
	}
	
	private void connect() {
		GGClient ggClient = config.getGGclient();
		RegistryInfo registry = config.getRegistryManager().getRandomRegistry();
		ggClient.connect(registry.getDomain(), registry.getPort())
		.addListener(f -> {
			if (!f.isSuccess()) {
				GGLoggerUtil.getLogger(this).info("Discovery Client Connect Server[{}:{}] Failed!",registry.getDomain(), registry.getPort());
				ggClient.schedule(config.getTryRegisterInterval(), () -> {
					connect();
				});
				return;
			}
			GGLoggerUtil.getLogger(this).info("Discovery Client Connect Server[{}:{}] Successfully!",registry.getDomain(), registry.getPort());
		});
	}

}
