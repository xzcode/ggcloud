package com.xzcode.ggcloud.discovery.client.events;

import com.xzcode.ggcloud.discovery.client.config.GGCDiscoveryClientConfig;

import xzcode.ggserver.core.common.event.IEventHandler;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.GGSessionUtil;

public class ConnCloseEventHandler implements IEventHandler<Void>{
	
	private GGCDiscoveryClientConfig config;
	
	public ConnCloseEventHandler(GGCDiscoveryClientConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void onEvent(Void data) {
		//连接关闭.立即移除服务信息
		GGSession session = GGSessionUtil.getSession();
	}


	
}
