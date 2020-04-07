package com.xzcode.ggcloud.eventbus.client.handler;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.common.message.resp.EventSubscribeResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 消息接收处理器
 *
 * @author zai
 * 2020-04-07 11:37:01
 */
public class ReceiveMessageRespHandler implements IRequestMessageHandler<EventSubscribeResp>{
	
	private EventbusClientConfig config;
	

	public ReceiveMessageRespHandler(EventbusClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<EventSubscribeResp> request) {
		EventSubscribeResp resp = request.getMessage();
	}

	

}
