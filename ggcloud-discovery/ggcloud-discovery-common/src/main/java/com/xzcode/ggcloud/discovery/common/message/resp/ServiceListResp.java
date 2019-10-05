package com.xzcode.ggcloud.discovery.common.message.resp;

import java.util.List;

import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;

/**
 * 客户端注册响应
 * 
 * 
 * @author zai
 * 2019-10-04 16:44:38
 */
public class ServiceListResp {
	
	public static final String ACTION = "service.list.resp";
	
	/**
	 * 服务列表
	 */
	private List<ServiceInfoModel> serviceList;

	public List<ServiceInfoModel> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceInfoModel> serviceList) {
		this.serviceList = serviceList;
	}
	
}
