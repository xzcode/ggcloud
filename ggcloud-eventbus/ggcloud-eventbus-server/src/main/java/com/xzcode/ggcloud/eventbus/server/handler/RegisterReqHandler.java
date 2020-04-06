package com.xzcode.ggcloud.eventbus.server.handler;

import java.util.List;

import com.xzcode.ggcloud.eventbus.common.message.req.EventSubscribeReq;
import com.xzcode.ggcloud.eventbus.common.message.req.model.ServiceInfoModel;
import com.xzcode.ggcloud.eventbus.common.message.resp.DiscoveryAddServiceResp;
import com.xzcode.ggcloud.eventbus.common.message.resp.AuthResp;
import com.xzcode.ggcloud.eventbus.common.service.ServiceInfo;
import com.xzcode.ggcloud.eventbus.common.service.ServiceManager;
import com.xzcode.ggcloud.eventbus.server.config.EventbusServerConfig;
import com.xzcode.ggcloud.eventbus.server.constant.DiscoveryServerSessionKeys;

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
public class RegisterReqHandler implements IRequestMessageHandler<EventSubscribeReq>{
	
	private EventbusServerConfig config;
	

	public RegisterReqHandler(EventbusServerConfig config) {
		super();
		this.config = config;
	}
	


	@Override
	public void handle(Request<EventSubscribeReq> request) {
		GGSession session = request.getSession();
		EventSubscribeReq req = request.getMessage();
		String serverAuthToken = config.getAuthToken();
		//判断认证token是否正确
		if (serverAuthToken != null && !serverAuthToken.isEmpty()) {
			String clientAuthToken = req.getAuthToken();
			if (clientAuthToken == null || clientAuthToken.isEmpty() || !clientAuthToken.equals(serverAuthToken)) {
				session.send(new AuthResp(false, "Auth Token Is Incorrect"));
				return;
			}
			
		}
		ServiceInfo infoModel = req.getServiceInfo();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		
		ServiceManager serviceManager = config.getServiceManager();
		
		if (serviceInfo == null) {
			serviceInfo = infoModel;
			serviceInfo.setHost(session.getHost());
			serviceInfo.setTimeoutDelay(config.getServiceTimeoutDelay());
			serviceInfo.setSession(session);
			serviceManager.registerService(serviceInfo);
			session.addAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, serviceInfo);
		}
		session.send(new AuthResp(true));
		
		//发送给所有服务客户端,新服务已上线
		serviceManager.sendToAllServices(new DiscoveryAddServiceResp(serviceInfo));
	}


	

}
