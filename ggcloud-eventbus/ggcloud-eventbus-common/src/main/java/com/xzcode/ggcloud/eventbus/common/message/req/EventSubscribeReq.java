package com.xzcode.ggcloud.eventbus.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 事件订阅请求
 *
 * @author zai
 * 2020-04-06 18:50:10
 */
public class EventSubscribeReq implements IMessage {
	
	public static final String ACTION_ID = "GG.EVENTBUS.EVENT.SUB.REQ";
	
	@Override
	public String getActionId() {
		return ACTION_ID;
	}
	
	//事件id
	private String eventId;
	

	public EventSubscribeReq() {
	}


	public EventSubscribeReq(String eventId) {
		super();
		this.eventId = eventId;
	}


	public String getEventId() {
		return eventId;
	}


	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	

	
}
