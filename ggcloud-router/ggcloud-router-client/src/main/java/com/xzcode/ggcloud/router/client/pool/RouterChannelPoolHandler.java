package com.xzcode.ggcloud.router.client.pool;

import io.netty.channel.Channel;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.common.handler.TcpChannelInitializer;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 路由通道池处理器
 * 
 * @author zai
 * 2019-12-19 15:34:06
 */
public class RouterChannelPoolHandler implements ChannelPoolHandler {
	
	protected GGClientConfig config;
	
	protected static final AttributeKey<String> PROTOCOL_TYPE_KEY = AttributeKey.valueOf(DefaultChannelAttributeKeys.PROTOCOL_TYPE);

	public RouterChannelPoolHandler(GGClientConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void channelReleased(Channel ch) throws Exception {
		
	}

	@Override
	public void channelAcquired(Channel ch) throws Exception {
		if (ch == null) {
			GGLoggerUtil.getLogger(this).warn("Acquired a null Channel!");
		}
	}

	@Override
	public void channelCreated(Channel ch) throws Exception {
		ch.pipeline().addLast(new TcpChannelInitializer(this.config));
	}

}
