package com.xzcode.ggcloud.eventbus.client.handler;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.client.subscriber.SubscriberManager;
import com.xzcode.ggcloud.eventbus.common.message.resp.EventMessageResp;
import com.xzcode.ggcloud.eventbus.common.message.resp.EventSubscribeResp;

import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.request.action.MessageDataHandler;

/**
 * 消息接收处理器
 *
 * @author zai
 * 2020-04-07 11:37:01
 */
public class ReceiveMessageRespHandler implements MessageDataHandler<EventMessageResp>{
	
	private EventbusClientConfig config;
	
	private ISerializer serializer;
	

	public ReceiveMessageRespHandler(EventbusClientConfig config) {
		super();
		this.config = config;
		this.serializer = this.config.getSessionGroupClient().getConfig().getSessionClient().getSerializer();
	}



	@Override
	public void handle(MessageData<EventMessageResp> messageData) {
		EventMessageResp resp = messageData.getMessage();
		String eventId = resp.getEventId();
		byte[] eventData = resp.getEventData();
		this.serializer.deserialize(eventData, t);
		
		
		
		SubscriberManager subscribeManager = this.config.getSubscribeManager();
		subscribeManager.trigger(eventId, eventbusMessage);
	}

	

}
