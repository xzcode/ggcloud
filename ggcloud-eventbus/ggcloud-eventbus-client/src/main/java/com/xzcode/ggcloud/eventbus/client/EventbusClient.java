package com.xzcode.ggcloud.eventbus.client;

import java.util.ArrayList;
import java.util.List;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.client.events.ConnCloseEventListener;
import com.xzcode.ggcloud.eventbus.client.events.ConnOpenEventListener;
import com.xzcode.ggcloud.eventbus.client.handler.AnthRespHandler;
import com.xzcode.ggcloud.eventbus.client.handler.EventPublishRespHandler;
import com.xzcode.ggcloud.eventbus.client.handler.EventSubscribeRespHandler;
import com.xzcode.ggcloud.eventbus.client.listener.IClientRegisterSuccessListener;
import com.xzcode.ggcloud.eventbus.client.registry.RegistryInfo;
import com.xzcode.ggcloud.eventbus.common.message.resp.AuthResp;
import com.xzcode.ggcloud.eventbus.common.message.resp.EventPublishResp;
import com.xzcode.ggcloud.eventbus.common.message.resp.EventSubscribeResp;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

public class EventbusClient {
	
	private EventbusClientConfig config;
	
	protected List<IClientRegisterSuccessListener> registerSuccessListeners = new ArrayList<>();
	
	public EventbusClient(EventbusClientConfig config) {
		this.config = config;
		this.config.setDiscoveryClient(this);
	}

	public void start() {
		GGClientConfig ggConfig = new GGClientConfig();
		ggConfig.setPingPongEnabled(true);
		ggConfig.setPrintPingPongInfo(config.isPrintPingPongInfo());
		ggConfig.setTaskExecutor(config.getTaskExecutor());
		ggConfig.setProtocolType(ProtocolTypeConstants.TCP);
		ggConfig.init();
		
		GGClient ggClient = new GGClient(ggConfig);
		config.setGGclient(ggClient);
		
		ggClient.onMessage(AuthResp.ACTION, new AnthRespHandler(config));
		ggClient.onMessage(EventPublishResp.ACTION, new EventPublishRespHandler(config));
		ggClient.onMessage(EventSubscribeResp.ACTION, new EventSubscribeRespHandler(config));
		
		ggClient.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		ggClient.addEventListener(GGEvents.Connection.OPENED, new ConnOpenEventListener(config));
		
		
		connect();
		
		
	}
	
	
	
	public void connect() {
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
	

}
