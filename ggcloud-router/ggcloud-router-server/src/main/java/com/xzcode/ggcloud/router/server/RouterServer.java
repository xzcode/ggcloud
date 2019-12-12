package com.xzcode.ggcloud.router.server;

import com.xzcode.ggcloud.router.server.config.RouterServerConfig;

import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.starter.IGGServerStarter;
import xzcode.ggserver.core.server.starter.impl.DefaultGGServerStarter;

/**
 * 路由服务器对象
 * 
 * @author zai
 * 2019-12-05 10:34:03
 */
public class RouterServer implements IGGServer<RouterServerConfig> {
	
	private RouterServerConfig config;

	private IGGServerStarter serverStarter;
	
	public RouterServer(RouterServerConfig serverConfig) {
		this.config = serverConfig;
	}

	@Override
	public RouterServerConfig getConfig() {
		return config;
	}

	@Override
	public void start() {
		this.shutdown();
		this.serverStarter = new DefaultGGServerStarter(config);
		this.serverStarter.start();
	}

	@Override
	public void shutdown() {
		if (this.serverStarter != null) {
			this.serverStarter.shutdown();
		}
	}
	
	
	
	
}
