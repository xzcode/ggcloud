package com.xzcode.ggcloud.discovery.server.events;

import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

public class ConnCloseEventListener implements IEventListener<Void>{
	
	private DiscoveryServerConfig config;


	public void setConfig(DiscoveryServerConfig config) {
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
		config.getServiceManager().removeServiceInfo(serviceInfo);			
		
		GGLoggerUtil.getLogger(this).warn("Service ungristry!");
		
	}

	
}
