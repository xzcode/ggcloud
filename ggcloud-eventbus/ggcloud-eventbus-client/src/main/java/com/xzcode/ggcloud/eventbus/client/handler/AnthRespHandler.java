package com.xzcode.ggcloud.eventbus.client.handler;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.common.message.resp.AuthResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class AnthRespHandler implements IRequestMessageHandler<AuthResp>{
	
	private EventbusClientConfig config;
	

	public AnthRespHandler(EventbusClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<AuthResp> request) {
		AuthResp resp = request.getMessage();
		if (resp.isSuccess()) {
			
		}
	}

	

}
