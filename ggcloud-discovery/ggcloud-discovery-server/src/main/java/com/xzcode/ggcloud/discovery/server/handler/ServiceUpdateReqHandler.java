package com.xzcode.ggcloud.discovery.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryServiceUpdateReq;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceListResp;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceUpdateResp;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;
import com.xzcode.ggcloud.discovery.server.services.ServiceManager;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 服务更新请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceUpdateReqHandler implements IRequestMessageHandler<DiscoveryServiceUpdateReq>{
	
	private DiscoveryServerConfig config;
	

	public ServiceUpdateReqHandler(DiscoveryServerConfig config) {
		this.config = config;
	}


	@Override
	public void handle(Request<DiscoveryServiceUpdateReq> request) {
		GGSession session = request.getSession();
		ServiceManager serviceManager = config.getServiceManager();
		
		DiscoveryServiceUpdateResp resp = new DiscoveryServiceUpdateResp();
		
		
		session.send(resp);
	}


}
