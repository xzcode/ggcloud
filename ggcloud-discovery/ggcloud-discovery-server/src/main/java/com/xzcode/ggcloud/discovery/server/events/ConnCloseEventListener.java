package com.xzcode.ggcloud.discovery.server.events;

import com.xzcode.ggcloud.discovery.server.config.GGCDiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.session.GGSession;

public class ConnCloseEventListener implements IEventListener<Void>{
	
	private GGCDiscoveryServerConfig config;


	public void setConfig(GGCDiscoveryServerConfig config) {
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> eventData) {
		//连接关闭.立即移除服务信息
		GGSession session = eventData.getSession();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		config.getServiceManager().removeServiceInfo(serviceInfo);
		
	}

	
}
