package com.xzcode.ggcloud.eventbus.client.handler;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.common.message.resp.EventPublishResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端认证请求
 *
 * @author zai
 * 2020-04-07 10:57:11
 */
public class EventPublishRespHandler implements IRequestMessageHandler<EventPublishResp>{
	
	private EventbusClientConfig config;
	

	public EventPublishRespHandler(EventbusClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<EventPublishResp> request) {
		EventPublishResp resp = request.getMessage();
	}

	

}
