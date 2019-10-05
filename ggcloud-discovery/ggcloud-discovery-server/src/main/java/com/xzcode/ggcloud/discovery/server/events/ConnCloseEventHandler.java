package com.xzcode.ggcloud.discovery.server.events;

import com.xzcode.ggcloud.discovery.server.config.GGCDiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;

import xzcode.ggserver.core.event.IEventHandler;
import xzcode.ggserver.core.session.GGSession;
import xzcode.ggserver.core.session.GGSessionUtil;

public class ConnCloseEventHandler implements IEventHandler<Void>{
	
	private GGCDiscoveryServerConfig config;

	@Override
	public void onEvent(Void data) {
		//连接关闭.立即移除服务信息
		GGSession session = GGSessionUtil.getSession();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		config.getServiceManager().removeServiceInfo(serviceInfo);
	}

	public void setConfig(GGCDiscoveryServerConfig config) {
		this.config = config;
	}

	
}
