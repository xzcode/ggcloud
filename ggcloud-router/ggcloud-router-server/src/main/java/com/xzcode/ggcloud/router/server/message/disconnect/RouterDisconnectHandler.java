package com.xzcode.ggcloud.router.server.message.disconnect;

import com.xzcode.ggcloud.router.common.message.disconnect.req.RouterDisconnectReq;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 路由断开连接处理器
 * 
 * @author zai
 * 2019-12-19 16:17:45
 */
public class RouterDisconnectHandler implements IRequestMessageHandler<RouterDisconnectReq>{

	@Override
	public void handle(Request<RouterDisconnectReq> request) {
		
	}

}
