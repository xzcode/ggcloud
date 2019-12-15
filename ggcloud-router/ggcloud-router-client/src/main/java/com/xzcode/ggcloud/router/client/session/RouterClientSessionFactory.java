package com.xzcode.ggcloud.router.client.session;

import io.netty.channel.Channel;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.factory.ISessionFactory;

public class RouterClientSessionFactory implements ISessionFactory {

	@Override
	public GGSession getSession(Channel channel, Pack pack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GGSession getSession(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void channelActive(Channel channel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void channelInActive(Channel channel) {
		// TODO Auto-generated method stub

	}

}
