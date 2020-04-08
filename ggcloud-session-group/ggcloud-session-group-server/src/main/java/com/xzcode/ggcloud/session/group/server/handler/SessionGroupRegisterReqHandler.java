package com.xzcode.ggcloud.session.group.server.handler;

import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;
import com.xzcode.ggcloud.session.group.common.message.req.SessionGroupRegisterReq;
import com.xzcode.ggcloud.session.group.common.message.resp.SessionGroupRegisterResp;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import io.netty.util.AttributeKey;
import xzcode.ggserver.core.common.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.prefebs.pingpong.model.GGPingPongInfo;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 内置ping处理器
 * 
 * @author zai
 * 2020-01-16 17:04:11
 */
public class SessionGroupRegisterReqHandler implements IRequestMessageHandler<SessionGroupRegisterReq> {
	
	protected SessionGroupServerConfig config;
	
	protected static final AttributeKey<GGPingPongInfo> PING_PONG_INFO_KEY = AttributeKey.valueOf(DefaultChannelAttributeKeys.PING_INFO);
	
	public SessionGroupRegisterReqHandler(SessionGroupServerConfig config) {
		this.config = config;
	}

	@Override
	public void handle(MessageData<SessionGroupRegisterReq> request) {
		GGSession session = request.getSession();
		SessionGroupRegisterReq req = request.getMessage();
		String groupId = req.getGroupId();
		GGSessionGroupManager sessionGroupManager = config.getSessionGroupManager();
		
		sessionGroupManager.addSession(groupId, session);
		session.setReady(true);
		session.send(new SessionGroupRegisterResp(true));
		
	}



}