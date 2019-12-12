package com.xzcode.ggcloud.router.server.session;

import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.router.server.message.disconnect.resp.RouterDisconnectResp;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.common.session.AbstractAttrMapSession;

public class RouterServerSession extends AbstractAttrMapSession<RouterServerConfig>{
	
	private Channel channel;
	
	public RouterServerSession(String sessionId, RouterServerConfig config, Channel channel) {
		super(sessionId, config);
		this.channel = channel;
	}

	@Override
	public IGGFuture<?> disconnect() {
		return send(new Response(RouterDisconnectResp.ACTION_ID, null));
	}

	@Override
	public boolean isActive() {
		return channel.isActive();
	}

	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public void setChannel(Channel channel) {
		this.channel = channel;		
	}

	

}

