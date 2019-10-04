package com.xzcode.ggcloud.detector.common.message.model.req;

/**
 * 客户端注册请求
 * 
 * 
 * @author zai
 * 2019-10-04 16:43:22
 */
public class ClientRegisterReq {
	
	public static final String ACTION = "clent.register.req";
	
	/**
	 * 认证token
	 */
	private String authToken;
	
	/**
	 * 服务名称
	 */
	private String serviceName;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
	
}
