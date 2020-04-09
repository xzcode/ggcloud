package com.xzcode.ggcloud.session.group.client.events;

import com.xzcode.ggcloud.session.group.client.config.SessionGroupClientConfig;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;

public class ConnCloseEventListener implements IEventListener<Void>{
	
	private SessionGroupClientConfig config;
	
	public ConnCloseEventListener(SessionGroupClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void onEvent(EventData<Void> eventData) {
		//断开连接后，创建新连接
		this.config.getSessionGroupClient().connectOne(config.getServerHost(), config.getServerPort());
	}


	
}
