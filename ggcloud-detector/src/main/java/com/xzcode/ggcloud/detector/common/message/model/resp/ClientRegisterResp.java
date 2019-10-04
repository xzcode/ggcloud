package com.xzcode.ggcloud.detector.common.message.model.resp;

/**
 * 客户端注册响应
 * 
 * 
 * @author zai
 * 2019-10-04 16:44:38
 */
public class ClientRegisterResp {
	
	public static final String ACTION = "clent.register.resp";
	
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
