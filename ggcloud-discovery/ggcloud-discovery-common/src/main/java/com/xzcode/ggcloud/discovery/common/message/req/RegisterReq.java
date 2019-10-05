package com.xzcode.ggcloud.discovery.common.message.req;

/**
 * 客户端注册请求
 * 
 * 
 * @author zai
 * 2019-10-04 16:43:22
 */
public class RegisterReq {
	
	public static final String ACTION = "register.req";
	
	/**
	 * 认证token
	 */
	private String authToken;
	
	/**
	 * 服务名称
	 */
	private String serviceName;
	/**
	 * 服务id
	 */
	private String serviceId;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	
	
}
