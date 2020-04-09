package com.xzcode.ggcloud.eventbus.client.events;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;

public class ConnOpenEventListener implements IEventListener<Void>{

	private EventbusClientConfig config;
	
	public ConnOpenEventListener(EventbusClientConfig config) {
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> e) {
	}

}
