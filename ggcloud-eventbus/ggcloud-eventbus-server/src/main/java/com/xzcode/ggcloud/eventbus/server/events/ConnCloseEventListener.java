package com.xzcode.ggcloud.eventbus.server.events;

import com.xzcode.ggcloud.eventbus.common.message.resp.DiscoveryServiceUnregisterResp;
import com.xzcode.ggcloud.eventbus.common.service.ServiceInfo;
import com.xzcode.ggcloud.eventbus.common.service.ServiceManager;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;
import com.xzcode.ggcloud.eventbus.server.constant.DiscoveryServerSessionKeys;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

public class ConnCloseEventListener implements IEventListener<Void>{
	
	private EventbusServerConfig config;

	public ConnCloseEventListener(EventbusServerConfig config) {
		super();
		this.config = config;
	}

	public void setConfig(EventbusServerConfig config) {
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> eventData) {
		//连接关闭.立即移除服务信息
		GGSession session = eventData.getSession();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		if (serviceInfo == null) {
			return;
		}
		config.getServiceManager().removeService(serviceInfo);
		DiscoveryServiceUnregisterResp resp = new DiscoveryServiceUnregisterResp();
		resp.setServiceName(serviceInfo.getServiceName());
		resp.setServiceId(serviceInfo.getServiceId());
		
		ServiceManager serviceManager = config.getServiceManager();
		serviceManager.sendToAllServices(resp);
		
		GGLoggerUtil.getLogger(this).warn("Service unregristry! serviceName: {}, serviceId: {}", serviceInfo.getServiceName(), serviceInfo.getServiceId());
		
	}

	
}
