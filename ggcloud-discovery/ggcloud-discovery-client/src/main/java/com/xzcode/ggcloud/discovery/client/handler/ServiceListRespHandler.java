package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.GGCDiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;

import xzcode.ggserver.core.message.receive.IOnMessageHandler;

/**
 * 注册响应处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceListRespHandler implements IOnMessageHandler<RegisterResp>{
	
	private GGCDiscoveryClientConfig config;
	

	public ServiceListRespHandler(GGCDiscoveryClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void onMessage(RegisterResp resp) {
		
	}

	

}
