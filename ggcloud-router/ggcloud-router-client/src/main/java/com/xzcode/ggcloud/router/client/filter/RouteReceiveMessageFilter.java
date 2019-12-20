package com.xzcode.ggcloud.router.client.filter;

import com.xzcode.ggcloud.router.client.config.RouterClientConfig;

import xzcode.ggserver.core.common.filter.IBeforeDeserializeFilter;
import xzcode.ggserver.core.common.message.Pack;

/**
 * 路由消息过滤器
 * 
 * @author zai
 * 2019-12-18 18:59:37
 */
public class RouteReceiveMessageFilter implements IBeforeDeserializeFilter{
	
	private RouterClientConfig config;
	
	public RouteReceiveMessageFilter(RouterClientConfig config) {
		this.config = config;
	}

	@Override
	public boolean doFilter(Pack pack) {
		//如果匹配不到路由,交由后续过滤器处理
		return !config.getRouterClient().route(pack);
	}

}
