package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientServiceManager;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceDownResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceDownRespHandler implements IRequestMessageHandler<DiscoveryServiceDownResp>{
	
	private DiscoveryClientConfig config;
	

	public ServiceDownRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<DiscoveryServiceDownResp> request) {
		DiscoveryServiceDownResp resp = request.getMessage();
		String serviceName = resp.getServiceName();
		String serviceId = resp.getServiceId();
		DiscoveryClientServiceManager serviceManager = config.getServiceManager();
		serviceManager.removeService(serviceName, serviceId);
	}

	

}
