package com.xzcode.ggcloud.router.client.session;

import io.netty.channel.Channel;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.session.AbstractAttrMapSession;

public class RouterClientSession extends AbstractAttrMapSession<GGClientConfig>{

	

	public RouterClientSession(String sessionId, GGClientConfig config) {
		super(sessionId, config);
	}

	@Override
	public IGGFuture disconnect() {
		return null;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public Channel getChannel() {
		return null;
	}

	@Override
	public void setChannel(Channel channel) {
		
	}


}
