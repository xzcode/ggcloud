package com.xzcode.ggcloud.router.server;

import com.xzcode.ggcloud.router.common.message.disconnect.req.RouterDisconnectReq;
import com.xzcode.ggcloud.router.common.message.ping.req.RouterPingReq;
import com.xzcode.ggcloud.router.common.message.ping.resp.RouterPingResp;
import com.xzcode.ggcloud.router.common.message.register.req.RouterChannelRegisterReq;
import com.xzcode.ggcloud.router.common.ping.RouterPingPongInfo;
import com.xzcode.ggcloud.router.server.config.RouterServerConfig;
import com.xzcode.ggcloud.router.server.message.disconnect.RouterDisconnectHandler;
import com.xzcode.ggcloud.router.server.message.register.RouterRegisterHandler;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.starter.IGGServerStarter;
import xzcode.ggserver.core.server.starter.impl.DefaultGGServerStarter;

/**
 * 路由服务器对象
 * 
 * @author zai
 * 2019-12-05 10:34:03
 */
public class RouterServer implements IGGServer {
	
	private RouterServerConfig config;

	private IGGServerStarter serverStarter;
	
	private static final String PING_PONG_INFO_KEY = "PING_PONG_INFO";
	private static final AttributeKey<RouterPingPongInfo> PING_PONG_INFO_ATTR_KEY = AttributeKey.valueOf(PING_PONG_INFO_KEY);
	
	public RouterServer(RouterServerConfig serverConfig) {
		this.config = serverConfig;
		if (!this.config.isInited()) {
			this.config.init();
		}
	}

	@Override
	public RouterServerConfig getConfig() {
		return config;
	}

	@Override
	public IGGFuture start() {
		this.shutdown();
		this.serverStarter = new DefaultGGServerStarter(config);
		IGGFuture startFuture = this.serverStarter.start();
		
		startFuture.addListener((f) -> {
			
			//添加预定义的消息处理器
			
			//添加通道组注册处理器
			RouterServer.this.onMessage(RouterChannelRegisterReq.ACTION_ID, new RouterRegisterHandler(this, this.config.getChannelGroupManager()));
			
			//添加通道端口连接处理器
			RouterServer.this.onMessage(RouterDisconnectReq.ACTION_ID, new RouterDisconnectHandler());
			
			/*
			
			RouterServer.this.addEventListener(GGEvents.Idle.ALL, (EventData<Void> eventData) -> {
				Channel channel = eventData.getChannel();
					
				RouterPingPongInfo pingPongInfo = channel.attr(PING_PONG_INFO_ATTR_KEY).get();
				
				if (pingPongInfo == null) {
					pingPongInfo = new RouterPingPongInfo();
					channel.attr(PING_PONG_INFO_ATTR_KEY).set(pingPongInfo);
				}
				
				if (pingPongInfo.isHeartBeatLost()) {
					channel.disconnect();
				}
				//增加心跳失败次数
				pingPongInfo.heartBeatLostTimesIncrease();
			});
			
			RouterServer.this.onMessage(RouterPingReq.ACTION_ID, (Request<RouterPingReq> request) -> {
				Channel channel = request.getChannel();
				channel.writeAndFlush(RouterServer.this.makePack(new Response(RouterPingResp.ACTION_ID, null)));
					
				RouterPingPongInfo pingPongInfo = channel.attr(PING_PONG_INFO_ATTR_KEY).get();
				if (pingPongInfo == null) {
					pingPongInfo = new RouterPingPongInfo();
					channel.attr(PING_PONG_INFO_ATTR_KEY).set(pingPongInfo);
				}
				//重置心跳失败次数
				pingPongInfo.heartBeatLostTimesReset();
			});
			*/
			
			
		});
		
		return startFuture;
	}

	@Override
	public void shutdown() {
		if (this.serverStarter != null) {
			this.serverStarter.shutdown();
		}
	}
	
	
	
	
}
