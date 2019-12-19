package com.xzcode.ggcloud.router.server.message.register;

import com.xzcode.ggcloud.router.common.message.register.req.RouterChannelRegisterReq;
import com.xzcode.ggcloud.router.server.constant.RouterServerChannelAttrKeys;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.common.channel.group.IChannelGroupManager;
import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;

/**
 * 路由通道组注册处理器
 * 
 * @author zai
 * 2019-12-19 16:13:42
 */
public class RouterRegisterHandler implements IRequestMessageHandler<RouterChannelRegisterReq>{
	
	protected AttributeKey<String> routerKey = AttributeKey.valueOf(RouterServerChannelAttrKeys.ROUTER_GROUP_ID_KEY);
	
	protected IChannelGroupManager channelGroupManager;
	
	
	

	public RouterRegisterHandler(IChannelGroupManager channelGroupManager) {
		this.channelGroupManager = channelGroupManager;
	}

	@Override
	public void handle(Request<RouterChannelRegisterReq> request) {
		System.out.println("RouterChannelRegisterReq ------->");
		Channel channel = request.getChannel();
		RouterChannelRegisterReq req = request.getMessage();
		String routerGroupId = req.getRouterGroupId();
		if (routerGroupId != null) {
			if (channel.attr(routerKey).get() == null) {
				channel.attr(routerKey).set(routerGroupId);
				channelGroupManager.addToChannelGroup(routerGroupId, channel);
			}
		}
		
		
		
	}

}
