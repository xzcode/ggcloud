package com.xzcode.ggcloud.discovery.server.handler;

import java.util.List;

import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryServiceRegisterReq;
import com.xzcode.ggcloud.discovery.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryAddServiceResp;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceRegisterResp;
import com.xzcode.ggcloud.discovery.common.service.ServiceInfo;
import com.xzcode.ggcloud.discovery.common.service.ServiceManager;
import com.xzcode.ggcloud.discovery.server.config.DiscoveryServerConfig;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;

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
public class RegisterReqHandler implements IRequestMessageHandler<DiscoveryServiceRegisterReq>{
	
	private DiscoveryServerConfig config;
	

	public RegisterReqHandler(DiscoveryServerConfig config) {
		super();
		this.config = config;
	}
	


	@Override
	public void handle(Request<DiscoveryServiceRegisterReq> request) {
		GGSession session = request.getSession();
		DiscoveryServiceRegisterReq req = request.getMessage();
		String serverAuthToken = config.getAuthToken();
		//判断认证token是否正确
		if (serverAuthToken != null && !serverAuthToken.isEmpty()) {
			String clientAuthToken = req.getAuthToken();
			if (clientAuthToken == null || clientAuthToken.isEmpty() || !clientAuthToken.equals(serverAuthToken)) {
				session.send(new DiscoveryServiceRegisterResp(false, "Auth Token Is Incorrect"));
				return;
			}
			
		}
		ServiceInfoModel infoModel = req.getServiceInfo();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		ServiceManager serviceManager = config.getServiceManager();
		List<ServiceInfo> serviceList = serviceManager.getServiceList();
		if (serviceInfo == null) {
			serviceInfo = new ServiceInfo();
			serviceInfo.setServiceName(infoModel.getServiceName());
			serviceInfo.setServiceId(infoModel.getServiceId());
			serviceInfo.setHost(session.getHost());
			serviceInfo.setPort(session.getPort());
			serviceInfo.setTimeoutDelay(config.getServiceTimeoutDelay());
			serviceInfo.setSession(session);
			serviceManager.registerService(serviceInfo);
			session.addAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, serviceInfo);
		}
		session.send(new DiscoveryServiceRegisterResp(true));
		
		//发送给所有服务客户端,新服务已上线
		for (ServiceInfo info : serviceList) {
			GGSession infoSession = info.getSession();
			infoSession.send(new DiscoveryAddServiceResp(serviceInfo));
		}
	}


	

}
