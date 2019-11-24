package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;

import xzcode.ggserver.core.common.message.receive.action.IRequestMessageAcion;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class RegisterRespHandler implements IRequestMessageAcion<RegisterResp>{
	
	private DiscoveryClientConfig config;
	

	public RegisterRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void onMessage(RegisterResp resp) {
		
	}

	

}
