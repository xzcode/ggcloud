package com.xzcode.ggcloud.discovery.common.message.req;

import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;

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
	
	//认证token
	private String authToken;
	
	//服务信息
	private ServiceInfoModel serviceInfo;
	

	public DiscoveryRegisterReq() {
	}

	public DiscoveryRegisterReq(ServiceInfoModel serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public ServiceInfoModel getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ServiceInfoModel serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
}
