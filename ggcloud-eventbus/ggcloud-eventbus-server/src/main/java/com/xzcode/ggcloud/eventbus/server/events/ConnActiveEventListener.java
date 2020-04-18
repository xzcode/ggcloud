package com.xzcode.ggcloud.eventbus.server.events;

import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;
import com.xzcode.ggserver.core.common.event.IEventListener;
import com.xzcode.ggserver.core.common.event.model.EventData;

public class ConnActiveEventListener implements IEventListener<Void>{
	
	private EventbusServerConfig config;


	public ConnActiveEventListener(EventbusServerConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> eventData) {
		
	}

}
