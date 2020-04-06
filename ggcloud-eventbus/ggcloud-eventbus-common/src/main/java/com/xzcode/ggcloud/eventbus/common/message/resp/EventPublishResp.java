package com.xzcode.ggcloud.eventbus.common.message.resp;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 事件订发布响应
 *
 * @author zai
 * 2020-04-06 18:50:10
 */
public class EventPublishResp implements IMessage {
	
	public static final String ACTION = "GG.EVENTBUS.EVENT.PUB.RESP";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	//事件id
	private String eventId;
	
	//事件数据
	private byte[] eventData;
	

	public EventPublishResp() {
		
	}


	


	public EventPublishResp(String eventId, byte[] eventData) {
		super();
		this.eventId = eventId;
		this.eventData = eventData;
	}





	public String getEventId() {
		return eventId;
	}


	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public byte[] getEventData() {
		return eventData;
	}
	public void setEventData(byte[] eventData) {
		this.eventData = eventData;
	}

	
}
