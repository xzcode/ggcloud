package com.xzcode.ggcloud.discovery.client.handler;

import com.xzcode.ggcloud.discovery.client.config.DiscoveryClientConfig;
import com.xzcode.ggcloud.discovery.common.message.resp.RegisterResp;

import xzcode.ggserver.core.common.message.request.action.IRequestMessageAcion;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 注册响应处理
 * 
 * 
 * @author zai
 * 2019-10-04 14:29:53
 */
public class ServiceListRespHandler implements IRequestMessageAcion<RegisterResp>{
	
	private DiscoveryClientConfig config;
	

	public ServiceListRespHandler(DiscoveryClientConfig config) {
		super();
		this.config = config;
	}


	@Override
	public void action(GGSession session, RegisterResp resp) {
		
	}

	

}
