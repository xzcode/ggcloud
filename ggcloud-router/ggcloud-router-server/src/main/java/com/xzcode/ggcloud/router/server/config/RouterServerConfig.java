package com.xzcode.ggcloud.router.server.config;

import com.xzcode.ggcloud.router.server.handler.codec.RouterDecodeHandler;
import com.xzcode.ggcloud.router.server.handler.codec.RouterEncodeHandler;

import xzcode.ggserver.core.server.config.GGServerConfig;

/**
 * 路由服务器配置 
 * 
 * @author zai
 * 2019-12-05 10:33:40
 */
public class RouterServerConfig extends GGServerConfig{

	@Override
	public void init() {
		this.decodeHandler = new RouterDecodeHandler();
		this.encodeHandler = new RouterEncodeHandler();
		super.init();
	}
	
	
	
	
}
