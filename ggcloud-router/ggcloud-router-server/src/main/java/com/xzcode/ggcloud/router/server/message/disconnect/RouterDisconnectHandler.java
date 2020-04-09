package com.xzcode.ggcloud.router.server.message.disconnect;

import com.xzcode.ggcloud.router.common.message.disconnect.req.RouterDisconnectReq;

import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.request.action.MessageDataHandler;

/**
 * 路由断开连接处理器
 * 
 * @author zai
 * 2019-12-19 16:17:45
 */
public class RouterDisconnectHandler implements MessageDataHandler<RouterDisconnectReq>{

	@Override
	public void handle(MessageData<RouterDisconnectReq> request) {
		
	}

}
