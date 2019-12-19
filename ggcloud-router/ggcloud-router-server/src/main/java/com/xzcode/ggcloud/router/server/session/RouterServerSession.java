package com.xzcode.ggcloud.router.server.session;

import com.xzcode.ggcloud.router.common.message.disconnect.resp.RouterDisconnectResp;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
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
	
	private String channelGroupId;
	
	public RouterServerSession(String sessionId, String channelGroupId,RouterServerConfig config) {
		super(sessionId, config);
		this.channelGroupId = channelGroupId;
	}

	@Override
	public IGGFuture disconnect() {
		emitEvent(GGEvents.Connection.CLOSED, new EventData<Object>(this, null));
		return send(new Response(RouterDisconnectResp.ACTION_ID, null));
	}

	@Override
	public Channel getChannel() {
		return config.getChannelGroupManager().getRandomChannel(channelGroupId);
	}

	@Override
	public void setChannel(Channel channel) {
		
	}
	
	public String getChannelGroupId() {
		return channelGroupId;
	}
	
	public void setChannelGroupId(String channelGroupId) {
		this.channelGroupId = channelGroupId;
	}

}

