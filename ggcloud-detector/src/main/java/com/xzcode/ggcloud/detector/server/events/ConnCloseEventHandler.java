package com.xzcode.ggcloud.detector.server.events;

import com.xzcode.ggcloud.detector.server.management.GGCDetectorServiceManager;

import xzcode.ggserver.core.event.IEventHandler;
import xzcode.ggserver.core.session.GGSession;
import xzcode.ggserver.core.session.GGSessionUtil;

public class ConnCloseEventHandler implements IEventHandler<Void>{
	
	private GGCDetectorServiceManager serviceManager;

	@Override
	public void onEvent(Void data) {
		//连接关闭.立即移除服务信息
		GGSession session = GGSessionUtil.getSession();
		serviceManager.removeServiceInfo(session);
	}

	public void setServiceManager(GGCDetectorServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	
}
