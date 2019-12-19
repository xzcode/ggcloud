package com.xzcode.ggcloud.router.server.session;

import com.xzcode.ggcloud.router.common.meta.RouterUserIdMetadata;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.router.server.constant.RouterServerChannelAttrKeys;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.factory.ISessionFactory;
import xzcode.ggserver.core.common.session.manager.ISessionManager;

/**
 * 路由服务器会话工厂
 * 
 * @author zai 2019-12-16 15:51:34
 */
public class RouterServerSessionFactory implements ISessionFactory {

	protected RouterServerConfig config;

	protected AttributeKey<String> routerKey = AttributeKey.valueOf(RouterServerChannelAttrKeys.ROUTER_GROUP_ID_KEY);

	public RouterServerSessionFactory(RouterServerConfig config) {
		this.config = config;
	}

	@Override
	public GGSession getSession(Channel channel, Request<?> request) {
		RouterUserIdMetadata metadata = request.getMetadata(RouterUserIdMetadata.class);
		if (metadata == null) {
			return null;
		}
		String userId = metadata.getUserId();

		if (userId == null) {
			return null;
			
		}
		ISessionManager sessionManager = config.getSessionManager();
		GGSession session = sessionManager.getSession(userId);
		if (session == null) {
			String channelGroupId = channel.attr(routerKey).get();
			session = new RouterServerSession(userId, channelGroupId, this.config);
			session = sessionManager.addSessionIfAbsent(session);

			request.setSession(session);
		}
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
