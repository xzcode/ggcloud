package com.xzcode.ggcloud.router.server.config;

import com.xzcode.ggcloud.router.common.meta.impl.RouterSessionIdMetadataProvider;
import com.xzcode.ggcloud.router.common.meta.impl.RouterSessionIdMetadataResolver;
import com.xzcode.ggcloud.router.server.session.RouterServerSessionFactory;

import xzcode.ggserver.core.common.channel.group.IChannelGroupManager;
import xzcode.ggserver.core.common.channel.group.impl.DefaultChannelGroupManager;
import xzcode.ggserver.core.server.config.GGServerConfig;

/**
 * 路由服务器配置 
 * 
 * @author zai
 * 2019-12-05 10:33:40
 */
public class RouterServerConfig extends GGServerConfig{
	
	protected IChannelGroupManager channelGroupManager;

	@Override
	public void init() {
		
		this.sessionFactory = new RouterServerSessionFactory(this);
		
		if (channelGroupManager == null) {
			channelGroupManager = new DefaultChannelGroupManager();
		}
		
		this.metadataResolver = new RouterSessionIdMetadataResolver(serializer);
		this.metadataProvider = new RouterSessionIdMetadataProvider();
		
		super.init();
	}

	public IChannelGroupManager getChannelGroupManager() {
		return channelGroupManager;
	}

	public void setChannelGroupManager(IChannelGroupManager channelGroupManager) {
		this.channelGroupManager = channelGroupManager;
	}
	
}
