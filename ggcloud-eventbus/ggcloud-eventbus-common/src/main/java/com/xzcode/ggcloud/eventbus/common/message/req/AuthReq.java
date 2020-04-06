package com.xzcode.ggcloud.eventbus.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 客户端认证请求
 *
 * @author zai
 * 2020-04-06 18:53:46
 */
public class AuthReq implements IMessage {
	
	public static final String ACTION = "GG.EVENTBUS.AUTH.REQ";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	//认证token
	private String authToken;
	

	public AuthReq() {
	}

	public AuthReq(String authToken) {
		this.authToken = authToken;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
}
