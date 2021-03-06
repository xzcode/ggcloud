package com.xzcode.ggcloud.session.group.server.events;

import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;

public class ConnCloseEventListener implements IEventListener<Void>{
	
	private SessionGroupServerConfig config;

	public ConnCloseEventListener(SessionGroupServerConfig config) {
		super();
		this.config = config;
	}

	public void setConfig(SessionGroupServerConfig config) {
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> eventData) {
		
		
	}

	
}
