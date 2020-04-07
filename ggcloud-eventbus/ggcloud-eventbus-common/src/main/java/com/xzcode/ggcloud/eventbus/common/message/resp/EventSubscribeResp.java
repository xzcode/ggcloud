package com.xzcode.ggcloud.eventbus.common.message.resp;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 事件订阅推送
 *
 * @author zai
 * 2020-04-07 11:31:03
 */
public class EventSubscribeResp implements IMessage {
	
	public static final String ACTION = "GG.EVENTBUS.EVENT.SUB.RESP";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	/**
	 * 是否订阅成功
	 */
	private boolean success;
	

	public EventSubscribeResp() {
	}


	public EventSubscribeResp(boolean success) {
		this.success = success;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
