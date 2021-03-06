package com.xzcode.ggcloud.discovery.common.message.resp;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 客户端注册响应
 * 
 * 
 * @author zai
 * 2019-10-04 16:44:38
 */
public class DiscoveryServiceRegisterResp implements IMessage{
	
	public static final String ACTION = "GG.DISCOVERY.SERVICE.REGISTER.RESP";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	/**
	 * 是否注册成功
	 */
	private boolean success;
	
	/**
	 * 响应码
	 */
	private int code;
	
	/**
	 * 消息
	 */
	private String message;
	

	public DiscoveryServiceRegisterResp(boolean success) {
		super();
		this.success = success;
	}
	public DiscoveryServiceRegisterResp(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public DiscoveryServiceRegisterResp() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
	
	
}
