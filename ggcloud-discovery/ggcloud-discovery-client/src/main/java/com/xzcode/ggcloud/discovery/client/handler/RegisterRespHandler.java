package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.GGCDiscoveryClient;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;

import xzcode.ggserver.core.message.receive.IOnMessageHandler;

/**
 * 客户端注册请求处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class RegisterRespHandler implements IOnMessageHandler<RegisterResp>{
	
	private GGCDiscoveryClient config;
	

	public RegisterRespHandler(GGCDiscoveryClient config) {
		super();
		this.config = config;
	}


	@Override
	public void onMessage(RegisterResp resp) {
		
	}

	

}
