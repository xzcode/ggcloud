package com.xzcode.ggcloud.router.client.pool;

import java.nio.charset.Charset;

import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.common.message.register.req.RouterChannelRegisterReq;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.common.handler.TcpChannelInitializer;
import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.common.message.response.support.IMakePackSupport;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 路由通道池处理器
 * 
 * @author zai
 * 2019-12-19 15:34:06
 */
public class RouterChannelPoolHandler implements ChannelPoolHandler, IMakePackSupport {
	
	protected GGClientConfig ggclientConfig;
	
	protected RouterClientConfig routerClientConfig;
	
	protected static final AttributeKey<String> PROTOCOL_TYPE_KEY = AttributeKey.valueOf(DefaultChannelAttributeKeys.PROTOCOL_TYPE);

	public RouterChannelPoolHandler(RouterClientConfig routerClientConfig, GGClientConfig ggclientConfig) {
		this.ggclientConfig = ggclientConfig;
		this.routerClientConfig = routerClientConfig;
	}

	@Override
	public void channelReleased(Channel ch) throws Exception {
		
	}

	@Override
	public void channelAcquired(Channel ch) throws Exception {
		
	}

	@Override
	public void channelCreated(Channel ch) throws Exception {
		ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
			@Override
			public void channelActive(ChannelHandlerContext ctx) throws Exception {
				ch.writeAndFlush(makePack(new Response(null, RouterChannelRegisterReq.ACTION_ID, new RouterChannelRegisterReq(routerClientConfig.getRouterGroup()))));
				super.channelActive(ctx);
				
			}
		});
		ch.pipeline().addLast(new TcpChannelInitializer(ggclientConfig));
		
		
	}

	@Override
	public Charset getCharset() {
		return ggclientConfig.getCharset();
	}

	@Override
	public ISerializer getSerializer() {
		return ggclientConfig.getSerializer();
	}

}
