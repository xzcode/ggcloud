package com.xzcode.ggcloud.discovery.common.message.resp;

import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 服务更新推送
 * 
 * @author zai
 * 2020-02-04 11:34:37
 */
public class DiscoveryServiceUpdateResp  implements IMessage{
	
	public static final String ACTION = "DISCOVERY.SERVICE.UPDATE.RESP";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	/**
	 * 服务列表
	 */
	private ServiceInfoModel serviceInfo;
	

	public DiscoveryServiceUpdateResp() {
		
	}

	public DiscoveryServiceUpdateResp(ServiceInfoModel serviceInfo) {
		super();
		this.serviceInfo = serviceInfo;
	}


	public ServiceInfoModel getServiceInfo() {
		return serviceInfo;
	}
	
	public void setServiceInfo(ServiceInfoModel serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	
}
