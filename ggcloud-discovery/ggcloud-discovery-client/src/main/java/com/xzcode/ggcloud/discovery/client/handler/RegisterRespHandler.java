package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.resp.DiscoveryRegisterResp;

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
public class RegisterRespHandler implements IRequestMessageHandler<DiscoveryRegisterResp>{
	
	private DiscoveryClientConfig config;
	

	public RegisterRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<DiscoveryRegisterResp> request) {
		DiscoveryRegisterResp resp = request.getMessage();
		System.out.println(resp);
		
		
	}

	

}
