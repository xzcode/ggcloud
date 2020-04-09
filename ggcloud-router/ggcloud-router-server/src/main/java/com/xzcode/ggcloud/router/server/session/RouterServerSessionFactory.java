package com.xzcode.ggcloud.router.server.session;

import com.xzcode.ggcloud.router.common.meta.RouterSessionIdMetadata;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.router.server.constant.RouterServerChannelAttrKeys;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.factory.ChannelSessionFactory;
import xzcode.ggserver.core.common.session.manager.ISessionManager;

/**
 * 路由服务器会话工厂
 * 
 * @author zai 2019-12-16 15:51:34
 */
public class RouterServerSessionFactory implements ChannelSessionFactory {

	protected RouterServerConfig config;

	protected AttributeKey<String> routerKey = AttributeKey.valueOf(RouterServerChannelAttrKeys.ROUTER_GROUP_ID_KEY);

	public RouterServerSessionFactory(RouterServerConfig config) {
		this.config = config;
	}

	@Override
	public GGSession getSession(Channel channel, MessageData<?> request) {
		RouterSessionIdMetadata metadata = request.getMetadata(RouterSessionIdMetadata.class);
		if (metadata == null) {
			return null;
		}
		String routerSessionId = metadata.getSessionId();

		if (routerSessionId == null) {
			return null;
			
		}
		ISessionManager sessionManager = config.getSessionManager();
		GGSession session = sessionManager.getSession(routerSessionId);
		if (session == null) {
			String channelGroupId = channel.attr(routerKey).get();
			if (channelGroupId == null) {
				return null;
			}
			GGSession newSession = new RouterServerSession(routerSessionId, channelGroupId, this.config);
			newSession.setChannel(channel);
			session = sessionManager.addSessionIfAbsent(newSession);
			if (session == null) {
				session = newSession;
				session.addAttribute(RouterSessionIdMetadata.METADATA_SESSION_KEY, metadata);
			}
		}
		request.setSession(session);
		return session;

	}

	@Override
	public GGSession getSession(Channel channel, Pack pack) {
		return null;
	}

	@Override
	public GGSession getSession(Channel channel) {
		return null;
	}

	@Override
	public void channelActive(Channel channel) {

	}

	@Override
	public void channelInActive(Channel channel) {

	}

}
