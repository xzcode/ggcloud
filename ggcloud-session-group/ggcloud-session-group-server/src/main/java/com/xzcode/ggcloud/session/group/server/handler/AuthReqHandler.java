package com.xzcode.ggcloud.session.group.server.handler;

import com.xzcode.ggcloud.session.group.common.message.req.AuthReq;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 客户端认证请求
 *
 * @author zai
 * 2020-04-07 10:57:11
 */
public class AuthReqHandler implements IRequestMessageHandler<AuthReq>{
	
	private SessionGroupServerConfig config;
	

	public AuthReqHandler(SessionGroupServerConfig config) {
		super();
		this.config = config;
	}



	@Override
	public void handle(Request<AuthReq> request) {
		AuthReq req = request.getMessage();
	}

	

}
