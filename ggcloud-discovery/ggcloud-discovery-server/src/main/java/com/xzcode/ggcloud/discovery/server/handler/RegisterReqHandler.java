package com.xzcode.ggcloud.discovery.server.handler;

import com.xzcode.ggcloud.discovery.common.message.req.RegisterReq;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;
import com.xzcode.ggcloud.discovery.common.services.ServiceInfo;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
import com.xzcode.ggcloud.discovery.server.util.ServiceIdUtil;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageAcion;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class RegisterReqHandler implements IRequestMessageAcion<RegisterReq>{
	
	private DiscoveryServerConfig config;
	

	public RegisterReqHandler(DiscoveryServerConfig config) {
		super();
		this.config = config;
	}
	


	@Override
	public void action(Request<RegisterReq> request) {
		GGSession session = request.getSession();
		RegisterReq req = request.getMessage();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		if (serviceInfo == null) {
			serviceInfo = new ServiceInfo();
			serviceInfo.setServiceName(req.getServiceName());
			serviceInfo.setServiceId(ServiceIdUtil.newServiceId());
			serviceInfo.setIp(session.getHost());
			serviceInfo.setPort(session.getPort());
			config.getServiceManager().registerService(serviceInfo);
		}
		config.getServer().send(session, RegisterResp.ACTION, new RegisterResp(true));
		
	}


	

}
