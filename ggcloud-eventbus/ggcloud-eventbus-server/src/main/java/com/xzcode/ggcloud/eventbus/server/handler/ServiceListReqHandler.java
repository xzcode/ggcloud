package com.xzcode.ggcloud.eventbus.server.handler;

import java.util.List;

import com.xzcode.ggcloud.eventbus.common.message.req.DiscoveryServiceListReq;
import com.xzcode.ggcloud.eventbus.common.message.resp.DiscoveryServiceListResp;
import com.xzcode.ggcloud.eventbus.common.service.ServiceInfo;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;

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
	
	private EventbusServerConfig config;

	public ServiceListReqHandler(EventbusServerConfig config) {
		this.config = config;
	}

	@Override
	public void handle(Request<DiscoveryServiceListReq> request) {
		GGSession session = request.getSession();
		List<ServiceInfo> serviceList = config.getServiceManager().getServiceList();
		DiscoveryServiceListResp resp = new DiscoveryServiceListResp(serviceList);
		session.send(resp);
	}


}
