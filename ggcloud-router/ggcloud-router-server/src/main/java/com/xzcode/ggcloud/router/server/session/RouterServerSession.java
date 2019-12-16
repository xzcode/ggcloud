package com.xzcode.ggcloud.router.server.session;

import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.router.server.message.disconnect.resp.RouterDisconnectResp;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.common.session.AbstractAttrMapSession;

/**
 * 路由服务器会话
 * 
 * @author zai
 * 2019-12-16 15:59:02
 */
public class RouterServerSession extends AbstractAttrMapSession<RouterServerConfig>{
	
	public RouterServerSession(String sessionId, RouterServerConfig config) {
		super(sessionId, config);
	}

	@Override
	public IGGFuture disconnect() {
		return send(new Response(RouterDisconnectResp.ACTION_ID, null));
	}

	@Override
	public Channel getChannel() {
		return null;
	}

	@Override
	public void setChannel(Channel channel) {
		
	}

}

