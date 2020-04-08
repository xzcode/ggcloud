package com.xzcode.ggcloud.eventbus.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.eventbus.client.config.SessionGroupClientConfig;
import com.xzcode.ggcloud.session.group.common.message.resp.SessionGroupRegisterResp;

import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 内置pingpong处理器
 * 
 * @author zai
 * 2020-01-16 17:04:11
 */
public class SessionGroupRegisterRespHandler implements IRequestMessageHandler<SessionGroupRegisterResp>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionGroupRegisterRespHandler.class);
	
	protected SessionGroupClientConfig config;
	
	public SessionGroupRegisterRespHandler(SessionGroupClientConfig config) {
		this.config = config;
	}

	@Override
	public void handle(Request<SessionGroupRegisterResp> request) {
		GGSession session = request.getSession();
		SessionGroupRegisterResp resp = request.getMessage();
		if (resp.isSuccess()) {
			
		}
	}
	


}
