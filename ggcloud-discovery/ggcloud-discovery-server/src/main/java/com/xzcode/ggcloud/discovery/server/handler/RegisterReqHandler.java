package com.xzcode.ggcloud.discovery.server.handler;

import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryRegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryRegisterResp;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
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
public class RegisterReqHandler implements IRequestMessageHandler<DiscoveryRegisterReq>{
	
	private DiscoveryServerConfig config;
	

	public RegisterReqHandler(DiscoveryServerConfig config) {
		super();
		this.config = config;
	}
	


	@Override
	public void handle(Request<DiscoveryRegisterReq> request) {
		GGSession session = request.getSession();
		DiscoveryRegisterReq req = request.getMessage();
		ServiceInfoModel infoModel = req.getServiceInfo();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		if (serviceInfo == null) {
			serviceInfo = new ServiceInfo();
			serviceInfo.setServiceName(infoModel.getServiceName());
			serviceInfo.setServiceId(infoModel.getServiceId());
			serviceInfo.setIp(session.getHost());
			serviceInfo.setPort(session.getPort());
			serviceInfo.setTimeoutDelay(config.getServiceTimeoutDelay());
			serviceInfo.setSession(session);
			config.getServiceManager().registerService(serviceInfo);
			session.addAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, serviceInfo);
		}
		session.send(new DiscoveryRegisterResp(true));
		
	}


	

}
