package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.GGCDiscoveryClient;
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
	
	private GGCDiscoveryClient config;
	

	public ServiceListRespHandler(GGCDiscoveryClient config) {
		super();
		this.config = config;
	}


	@Override
	public void onMessage(RegisterResp resp) {
		
	}

	

}
