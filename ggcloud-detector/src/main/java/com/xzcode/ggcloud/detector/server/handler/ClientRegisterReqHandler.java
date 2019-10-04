package com.xzcode.ggcloud.detector.server.handler;

import com.xzcode.ggcloud.detector.common.message.model.req.ClientRegisterReq;
import com.xzcode.ggcloud.detector.server.management.GGCDetectorServiceInfo;
import com.xzcode.ggcloud.detector.server.management.GGCDetectorServiceManager;

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
public class ClientRegisterReqHandler implements IOnMessageHandler<ClientRegisterReq>{
	
	private GGCDetectorServiceManager serviceManager;

	@Override
	public void onMessage(ClientRegisterReq req) {
		GGSession session = GGSessionUtil.getSession();
		GGCDetectorServiceInfo serviceInfo = serviceManager.getServiceInfo(session);
		if (serviceInfo != null) {
			
			return;
		}
		String serviceName = req.getServiceName();
	}

	public void setServiceManager(GGCDetectorServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	
	

}
