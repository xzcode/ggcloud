package com.xzcode.ggcloud.discovery.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 客户端注册请求
 * 
 * 
 * @author zai
 * 2019-10-04 16:43:22
 */
public class DiscoveryRegisterReq implements IMessage {
	
	public static final String ACTION = "DISCOVERY.REGISTER.REQ";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
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
	
	/**
	 * 地区
	 */
	private String region;
	
	/**
	 * 区域
	 */
	private String zone;

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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	
	
	
	
}
