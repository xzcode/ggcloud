package com.xzcode.ggcloud.discovery.client.events;

import com.xzcode.ggcloud.discovery.client.GGCDiscoveryClient;

import xzcode.ggserver.core.event.IEventHandler;
import xzcode.ggserver.core.session.GGSession;
import xzcode.ggserver.core.session.GGSessionUtil;

public class ConnCloseEventHandler implements IEventHandler<Void>{
	
	private GGCDiscoveryClient config;
	
	public ConnCloseEventHandler(GGCDiscoveryClient config) {
		super();
		this.config = config;
	}

	@Override
	public void onEvent(Void data) {
		//连接关闭.立即移除服务信息
		GGSession session = GGSessionUtil.getSession();
	}


	
}
