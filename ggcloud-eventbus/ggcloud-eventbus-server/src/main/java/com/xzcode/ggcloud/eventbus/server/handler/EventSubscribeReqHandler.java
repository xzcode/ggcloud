package com.xzcode.ggcloud.eventbus.server.handler;

import com.xzcode.ggcloud.eventbus.common.message.req.EventSubscribeReq;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;

import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.request.action.MessageDataHandler;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 客户端认证请求
 *
 * @author zai
 * 2020-04-07 10:57:11
 */
public class EventSubscribeReqHandler implements MessageDataHandler<EventSubscribeReq>{
	
	private EventbusServerConfig config;
	

	public EventSubscribeReqHandler(EventbusServerConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(MessageData<EventSubscribeReq> messageData) {
		EventSubscribeReq req = messageData.getMessage();
		String eventId = req.getEventId();
		GGSession session = messageData.getSession();
		//添加监听
		this.config.getSubscriptionManager().addSubscription(eventId, session);
	}

	

}
