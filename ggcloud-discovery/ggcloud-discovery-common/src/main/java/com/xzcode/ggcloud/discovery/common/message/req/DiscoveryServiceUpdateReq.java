package com.xzcode.ggcloud.discovery.common.message.req;

import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 服务更新请求
 * 
 * @author zai
 * 2020-02-04 15:30:56
 */
public class DiscoveryServiceUpdateReq implements IMessage{
	
	public static final String ACTION = "DISCOVERY.SERVICE.UPDATE.REQ";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
	//服务信息
	private ServiceInfoModel serviceInfo;
	
	

	public DiscoveryServiceUpdateReq() {
	}
	
	

	public DiscoveryServiceUpdateReq(ServiceInfoModel serviceInfo) {
		this.serviceInfo = serviceInfo;
	}



	public ServiceInfoModel getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ServiceInfoModel serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	
	
	
}
