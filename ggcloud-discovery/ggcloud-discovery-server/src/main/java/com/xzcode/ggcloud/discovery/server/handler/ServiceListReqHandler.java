package com.xzcode.ggcloud.discovery.server.handler;

import com.xzcode.ggcloud.discovery.common.message.req.RegisterReq;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;
import com.xzcode.ggcloud.discovery.server.config.GGCDiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;
import com.xzcode.ggcloud.discovery.server.util.ServiceIdUtil;

import xzcode.ggserver.core.message.receive.IOnMessageHandler;
import xzcode.ggserver.core.session.GGSession;
import xzcode.ggserver.core.session.GGSessionUtil;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceListReqHandler implements IOnMessageHandler<RegisterReq>{
	
	private GGCDiscoveryServerConfig config;
	

	public ServiceListReqHandler(GGCDiscoveryServerConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void onMessage(RegisterReq req) {
		GGSession session = GGSessionUtil.getSession();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		if (serviceInfo == null) {
			serviceInfo = new ServiceInfo();
			serviceInfo.setSession(session);
			serviceInfo.setServiceName(req.getServiceName());
			serviceInfo.setServiceId(ServiceIdUtil.newServiceId());
			serviceInfo.setIp(session.getHost());
			serviceInfo.setPort(session.getPort());
			config.getServiceManager().registerService(serviceInfo);
		}
		config.getGgServer().send(RegisterResp.ACTION, new RegisterResp(true));
		
	}

	

}
