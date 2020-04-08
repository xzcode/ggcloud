package com.xzcode.ggcloud.eventbus.client.events;

import com.xzcode.ggcloud.eventbus.client.config.SessionGroupClientConfig;

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
		
	}


	
}
