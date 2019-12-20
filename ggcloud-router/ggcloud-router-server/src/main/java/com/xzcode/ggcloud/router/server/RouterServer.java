package com.xzcode.ggcloud.router.server;

import com.xzcode.ggcloud.router.common.message.disconnect.req.RouterDisconnectReq;
import com.xzcode.ggcloud.router.common.message.register.req.RouterChannelRegisterReq;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.router.server.message.disconnect.RouterDisconnectHandler;
import com.xzcode.ggcloud.router.server.message.register.RouterRegisterHandler;

import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.starter.IGGServerStarter;
import xzcode.ggserver.core.server.starter.impl.DefaultGGServerStarter;

/**
 * 路由服务器对象
 * 
 * @author zai
 * 2019-12-05 10:34:03
 */
public class RouterServer implements IGGServer {
	
	private RouterServerConfig config;

	private IGGServerStarter serverStarter;
	
	public RouterServer(RouterServerConfig serverConfig) {
		this.config = serverConfig;
		if (!this.config.isInited()) {
			this.config.init();
		}
	}

	@Override
	public RouterServerConfig getConfig() {
		return config;
	}

	@Override
	public IGGFuture start() {
		this.shutdown();
		this.serverStarter = new DefaultGGServerStarter(config);
		IGGFuture startFuture = this.serverStarter.start();
		
		startFuture.addListener((f) -> {
			
			//添加预定义的消息处理器
			
			//添加通道组注册处理器
			RouterServer.this.onMessage(RouterChannelRegisterReq.ACTION_ID, new RouterRegisterHandler(this, this.config.getChannelGroupManager()));
			
			//添加通道端口连接处理器
			RouterServer.this.onMessage(RouterDisconnectReq.ACTION_ID, new RouterDisconnectHandler());
			
		});
		
		return startFuture;
	}

	@Override
	public void shutdown() {
		if (this.serverStarter != null) {
			this.serverStarter.shutdown();
		}
	}
	
	
	
	
}
