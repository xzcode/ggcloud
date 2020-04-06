package com.xzcode.ggcloud.eventbus.common.message.resp;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 事件订阅响应
 *
 * @author zai
 * 2020-04-06 18:50:10
 */
public class EventSubscribeResp implements IMessage {
	
	public static final String ACTION = "GG.EVENTBUS.EVENT.SUB.RESP";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	/**
	 * 是否注册成功
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
