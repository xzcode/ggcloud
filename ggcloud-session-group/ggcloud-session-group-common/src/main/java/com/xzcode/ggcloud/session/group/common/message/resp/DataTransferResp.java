package com.xzcode.ggcloud.session.group.common.message.resp;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 数据传输推送
 *
 * @author zai
 * 2020-04-08 10:32:51
 */
public class DataTransferResp implements IMessage {

	public static final String ACTION = "GG.SESSION.GROUP.DATA.TRANSFER.RESP";

	@Override
	public String getActionId() {
		return ACTION;
	}

	// 事件id
	private String eventId;

	// 事件数据
	private byte[] eventData;

	public DataTransferResp() {

	}

	public DataTransferResp(String eventId, byte[] eventData) {
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
