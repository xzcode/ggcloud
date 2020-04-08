package com.xzcode.ggcloud.session.group.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 数据传输请求
 *
 * @author zai
 * 2020-04-08 10:31:48
 */
public class DataTransferReq implements IMessage {
	
	public static final String ACTION = "GG.SESSION.GROUP.PUB.REQ";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	//事件id
	private String eventId;
	
	//事件数据
	private byte[] eventData;
	

	public DataTransferReq() {
		
	}


	


	public DataTransferReq(String eventId, byte[] eventData) {
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
