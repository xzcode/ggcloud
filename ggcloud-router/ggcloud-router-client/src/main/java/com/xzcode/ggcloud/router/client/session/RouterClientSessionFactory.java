package com.xzcode.ggcloud.router.client.session;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.factory.ISessionFactory;

/**
 * 路由客户端会话工厂
 * 
 * @author zai
 * 2019-12-16 19:15:16
 */
public class RouterClientSessionFactory implements ISessionFactory {
	

	@Override
	public GGSession getSession(Channel channel, Request<?> request) {
		return null;
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
