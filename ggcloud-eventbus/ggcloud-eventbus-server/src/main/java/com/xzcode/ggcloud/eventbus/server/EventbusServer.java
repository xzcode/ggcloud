package com.xzcode.ggcloud.eventbus.server;

import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;

public class EventbusServer {
	
	private EventbusServerConfig config;
	
	
	
	public EventbusServer(EventbusServerConfig config) {
		super();
		this.config = config;
	}

	public void start() {
		
		
	}
	
	public void setConfig(EventbusServerConfig config) {
		this.config = config;
	}
	
	public EventbusServerConfig getConfig() {
		return config;
	}
	
}
