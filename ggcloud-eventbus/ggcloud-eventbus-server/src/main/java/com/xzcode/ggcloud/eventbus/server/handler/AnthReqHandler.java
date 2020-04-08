package com.xzcode.ggcloud.eventbus.server.handler;

import com.xzcode.ggcloud.eventbus.common.message.req.AuthReq;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;

import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端认证请求
 *
 * @author zai
 * 2020-04-07 10:57:11
 */
public class AnthReqHandler implements IRequestMessageHandler<AuthReq>{
	
	private EventbusServerConfig config;
	

	public AnthReqHandler(EventbusServerConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(MessageData<AuthReq> request) {
		AuthReq req = request.getMessage();
	}

	

}
