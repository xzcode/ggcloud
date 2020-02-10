package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceUnregisterResp;
import com.xzcode.ggcloud.discovery.common.service.ServiceManager;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceUnregisterRespHandler implements IRequestMessageHandler<DiscoveryServiceUnregisterResp>{
	
	private DiscoveryClientConfig config;
	

	public ServiceUnregisterRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<DiscoveryServiceUnregisterResp> request) {
		DiscoveryServiceUnregisterResp resp = request.getMessage();
		String serviceName = resp.getServiceName();
		String serviceId = resp.getServiceId();
		ServiceManager serviceManager = config.getServiceManager();
		serviceManager.removeService(serviceName, serviceId);
	}

	

}
