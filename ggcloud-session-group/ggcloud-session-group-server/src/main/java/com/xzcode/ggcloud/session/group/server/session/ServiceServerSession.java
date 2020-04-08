package com.xzcode.ggcloud.session.group.server.session;

import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.config.GGConfig;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.impl.AbstractAttrMapSession;

public class ServiceServerSession extends AbstractAttrMapSession<GGConfig>{
	
	protected String groupId;
	
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

	

}
