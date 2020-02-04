package com.xzcode.ggcloud.discovery.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryServiceListReq;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceListResp;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceListReqHandler implements IRequestMessageHandler<DiscoveryServiceListReq>{
	
	private DiscoveryServerConfig config;
	

	public ServiceListReqHandler(DiscoveryServerConfig config) {
		this.config = config;
	}


	@Override
	public void handle(Request<DiscoveryServiceListReq> request) {
		GGSession session = request.getSession();
		List<ServiceInfo> serviceList = config.getServiceManager().getServiceList();
		List<ServiceInfoModel> serviceModelList = new ArrayList<>();
		for (ServiceInfo info : serviceList) {
			ServiceInfoModel model = new ServiceInfoModel();
			model.setServiceName(info.getServiceName());
			model.setServiceId(info.getServiceId());
			model.setZone(info.getZone());
			model.setRegion(info.getZone());
			model.setIp(info.getIp());
			model.setPort(info.getPort());
			model.setExtraData(info.getExtraData());
			serviceModelList.add(model);
		}
		
		DiscoveryServiceListResp resp = new DiscoveryServiceListResp(serviceModelList);
		
		
		session.send(resp);
	}


}
