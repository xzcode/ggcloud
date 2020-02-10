package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.req.DiscoveryServiceListReq;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryServiceRegisterResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class RegisterRespHandler implements IRequestMessageHandler<DiscoveryServiceRegisterResp>{
	
	private DiscoveryClientConfig config;
	

	public RegisterRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<DiscoveryServiceRegisterResp> request) {
		DiscoveryServiceRegisterResp resp = request.getMessage();
		if (resp.isSuccess()) {
			config.getSession().send(DiscoveryServiceListReq.DEFAULT_INSTANT);
		}
	}

	

}
