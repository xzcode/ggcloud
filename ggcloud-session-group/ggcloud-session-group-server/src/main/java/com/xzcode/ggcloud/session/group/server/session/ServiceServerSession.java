package com.xzcode.ggcloud.session.group.server.session;

import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.config.GGConfig;
import xzcode.ggserver.core.common.future.GGDefaultFuture;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.impl.AbstractAttrMapSession;
import xzcode.ggserver.core.common.session.listener.ISessionDisconnectListener;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 业务服务端session
 *
 * @author zai
 * 2020-04-09 10:11:53
 */
public class ServiceServerSession extends AbstractAttrMapSession<GGConfig>{
	
	//会话组id
	protected String groupId;
	
	//会话组管理器
	protected GGSessionGroupManager sessionGroupManager;

	public ServiceServerSession(String sessionId, GGSessionGroupManager sessionGroupManager,GGConfig config) {
		super(sessionId, config);
		this.sessionGroupManager = sessionGroupManager;
	}

	@Override
	public Channel getChannel() {
		return null;
	}

	@Override
	public void setChannel(Channel channel) {
		
	}


	@Override
	public IGGFuture send(Pack pack) {
		return sessionGroupManager.sendToRandomOne(groupId, pack);
	}

	@Override
	public IGGFuture disconnect() {
		
		triggerDisconnectListeners();
		
		GGDefaultFuture future = new GGDefaultFuture();
		future.setSession(this);
		future.setDone(true);
		future.setSuccess(true);
		return future;
	}

	
	

}
