package com.xzcode.ggcloud.session.group.server.events;

import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;

public class ConnActiveEventListener implements IEventListener<Void>{
	
	private SessionGroupServerConfig config;


	public ConnActiveEventListener(SessionGroupServerConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> eventData) {
		
	}

}
