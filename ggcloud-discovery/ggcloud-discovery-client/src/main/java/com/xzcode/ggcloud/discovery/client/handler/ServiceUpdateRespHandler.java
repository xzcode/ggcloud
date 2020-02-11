package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceUpdateResp;
import com.xzcode.ggcloud.discovery.common.service.ServiceInfo;
import com.xzcode.ggcloud.discovery.common.service.ServiceManager;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 服务更新推送处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceUpdateRespHandler implements IRequestMessageHandler<DiscoveryServiceUpdateResp>{
	
	private DiscoveryClientConfig config;
	

	public ServiceUpdateRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void handle(Request<DiscoveryServiceUpdateResp> request) {
		
		DiscoveryServiceUpdateResp resp = request.getMessage();
		ServiceInfo updateModel = resp.getServiceInfo();
		if (updateModel == null) {
			return;
		}
		
		ServiceManager serviceManager = config.getServiceManager();
		ServiceInfo service = serviceManager.getService(updateModel.getServiceId());
		if (service != null) {
			service.getCustomData().putAll(updateModel.getCustomData());
			serviceManager.updateService(service);
		}
		
	}

	

}
