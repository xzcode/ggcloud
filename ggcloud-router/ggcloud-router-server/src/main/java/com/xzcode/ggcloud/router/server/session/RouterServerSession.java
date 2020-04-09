package com.xzcode.ggcloud.router.server.session;

import com.xzcode.ggcloud.router.common.message.disconnect.resp.RouterDisconnectResp;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.common.session.impl.AbstractAttrMapSession;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 路由服务器会话
 * 
 * @author zai
 * 2019-12-16 15:59:02
 */
public class RouterServerSession extends AbstractAttrMapSession<RouterServerConfig>{
	
	private String channelGroupId;
	private Channel channel;
	
	public RouterServerSession(String sessionId, String channelGroupId,RouterServerConfig config) {
		super(sessionId, config);
		this.channelGroupId = channelGroupId;
	}

	@Override
	public IGGFuture disconnect() {
		emitEvent(new EventData<Object>(this, GGEvents.Connection.CLOSED, null));
		config.getSessionManager().remove(this.getSessonId());
		return send(new MessageData<>(RouterDisconnectResp.ACTION_ID, null));
	}

	@Override
	public Channel getChannel() {
		if (channelGroupId == null) {
			GGLoggerUtil.getLogger(this.getClass()).error("RouterServerSession Cannot get channel, 'channelGroupId' is null!");
			return null;
		}
		if (this.channel == null || !channel.isActive()) {
			this.channel = config.getChannelGroupManager().getRandomChannel(channelGroupId);
		}
		return this.channel;
		
	}

	@Override
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public String getChannelGroupId() {
		return channelGroupId;
	}
	
	public void setChannelGroupId(String channelGroupId) {
		this.channelGroupId = channelGroupId;
	}

}

