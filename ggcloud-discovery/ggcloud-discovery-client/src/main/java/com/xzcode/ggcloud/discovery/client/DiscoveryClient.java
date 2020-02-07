package com.xzcode.ggcloud.discovery.client;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.handler.RegisterRespHandler;
import com.xzcode.ggcloud.discovery.client.handler.ServiceListRespHandler;
import com.xzcode.ggcloud.discovery.client.registry.RegistryInfo;
import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryRegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryServiceUpdateReq;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
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
		ggConfig.setPingPongEnabled(true);
		ggConfig.setWorkThreadSize(1);
		ggConfig.setProtocolType(ProtocolTypeConstants.TCP);
		ggConfig.setWorkerGroupThreadFactory(new GGThreadFactory("discovery-worker-", false));
		ggConfig.init();
		
		GGClient ggClient = new GGClient(ggConfig);
		config.setGGclient(ggClient);
		
		ggClient.onMessage(DiscoveryRegisterResp.ACTION, new RegisterRespHandler(config));
		ggClient.onMessage(DiscoveryServiceListResp.ACTION, new ServiceListRespHandler(config));
		
		ggClient.addEventListener(GGEvents.Connection.CLOSED, e -> {
			config.getRegistryManager().getRegistriedInfo().setActive(false);
			//断开连接，触发重连
			connect();
		});
		ggClient.addEventListener(GGEvents.Connection.OPENED, e -> {
			config.getRegistryManager().getRegistriedInfo().setActive(true);
			
			//打开连接，发送注册请求
			//发送注册请求
			GGSession session = e.getSession();
			config.setSession(session);
			
			DiscoveryRegisterReq req = new DiscoveryRegisterReq();
			req.setAuthToken(config.getAuthToken());
			ServiceInfoModel serviceInfo = new ServiceInfoModel();
			
			serviceInfo.setRegion(config.getRegion());
			serviceInfo.setZone(config.getZone());
			serviceInfo.setServiceId(config.getServiceId());
			serviceInfo.setServiceName(config.getServiceName());
			
			req.setServiceInfo(serviceInfo);
			
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
				//连接失败，进行进行重连操作
				GGLoggerUtil.getLogger(this).info("Discovery Client Connect Server[{}:{}] Failed!",registry.getDomain(), registry.getPort());
				ggClient.schedule(config.getTryRegisterInterval(), () -> {
					connect();
				});
				return;
			}
			GGLoggerUtil.getLogger(this).info("Discovery Client Connect Server[{}:{}] Successfully!",registry.getDomain(), registry.getPort());
		});
	}
	
	/**
	 * 更新服务
	 * 
	 * @author zai
	 * 2020-02-04 17:11:08
	 */
	public void updateService() {
		GGSession session = config.getSession();
		DiscoveryServiceUpdateReq req = new DiscoveryServiceUpdateReq();
		ServiceInfoModel serviceInfo = new ServiceInfoModel();
		
		serviceInfo.setRegion(config.getRegion());
		serviceInfo.setZone(config.getZone());
		serviceInfo.setServiceId(config.getServiceId());
		serviceInfo.setServiceName(config.getServiceName());
		
		serviceInfo.setExtraData(config.getExtraData());
		
		req.setServiceInfo(serviceInfo);
		session.send(req);

	}
	
	
	public DiscoveryClientConfig getConfig() {
		return config;
	}

}
