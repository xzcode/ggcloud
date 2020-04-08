package com.xzcode.ggcloud.eventbus.client.handler;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.common.message.resp.AuthResp;

import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端认证请求
 *
 * @author zai
 * 2020-04-07 10:57:11
 */
public class AnthRespHandler implements IRequestMessageHandler<AuthResp>{
	
	private EventbusClientConfig config;
	

	public AnthRespHandler(EventbusClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(MessageData<AuthResp> request) {
		AuthResp resp = request.getMessage();
		if (resp.isSuccess()) {
			
		}
	}

	

}
