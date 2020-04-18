package com.xzcode.ggcloud.discovery.server.events;

import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggserver.core.common.event.IEventListener;
import com.xzcode.ggserver.core.common.event.model.EventData;

public class ConnActiveEventListener implements IEventListener<Void>{
	
	private DiscoveryServerConfig config;


	public ConnActiveEventListener(DiscoveryServerConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> eventData) {
		
	}

}
