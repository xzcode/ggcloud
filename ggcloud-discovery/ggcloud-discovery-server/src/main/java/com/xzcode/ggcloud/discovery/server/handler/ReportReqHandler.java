package com.xzcode.ggcloud.discovery.server.handler;

import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryReportReq;
import com.xzcode.ggcloud.discovery.server.constant.DiscoveryServerSessionKeys;
import com.xzcode.ggcloud.discovery.server.services.ServiceInfo;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 服务定时报告请求处理器
 * 
 * @author zai
 * 2020-02-04 15:17:37
 */
public class ReportReqHandler implements IRequestMessageHandler<DiscoveryReportReq>{

	@Override
	public void handle(Request<DiscoveryReportReq> request) {
		GGSession session = request.getSession();
		ServiceInfo serviceInfo = session.getAttribute(DiscoveryServerSessionKeys.SERVICE_INFO, ServiceInfo.class);
		if (serviceInfo != null) {
			//刷新超时时间
			serviceInfo.refreshExpireTime();
		}
		
	}


	


}
