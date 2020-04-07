package com.xzcode.ggcloud.eventbus.common.message.resp;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 事件消息推送
 *
 * @author zai
 * 2020-04-07 11:34:50
 */
public class EventMessageResp implements IMessage {

	public static final String ACTION = "GG.EVENTBUS.REC.MSG.RESP";

	@Override
	public String getActionId() {
		return ACTION;
	}

	// 事件id
	private String eventId;

	// 事件数据
	private byte[] eventData;

	public EventMessageResp() {

	}

	public EventMessageResp(String eventId, byte[] eventData) {
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
