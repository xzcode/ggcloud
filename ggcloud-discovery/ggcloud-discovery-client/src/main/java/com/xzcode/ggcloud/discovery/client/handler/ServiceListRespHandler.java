package com.xzcode.ggcloud.discovery.client.handler;

import java.util.List;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientServiceInfo;
import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientServiceManager;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceListResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 注册响应处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceListRespHandler implements IRequestMessageHandler<DiscoveryServiceListResp>{
	
	private DiscoveryClientConfig config;
	

	public ServiceListRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void handle(Request<DiscoveryServiceListResp> request) {
		
		DiscoveryServiceListResp resp = request.getMessage();
		List<ServiceInfoModel> serviceList = resp.getServiceList();
		if (serviceList == null) {
			return;
		}
		
		DiscoveryClientServiceManager serviceManager = config.getServiceManager();
		for (ServiceInfoModel model : serviceList) {
			DiscoveryClientServiceInfo serviceInfo = new DiscoveryClientServiceInfo();
			serviceInfo.setServiceName(model.getServiceName());
			serviceInfo.setServiceId(model.getServiceId());
			serviceInfo.setIp(model.getIp());
			serviceInfo.setPort(model.getPort());
			serviceInfo.setExtraData(model.getExtraData());
			serviceManager.registerService(serviceInfo);
		}
		
		
	}

	

}
