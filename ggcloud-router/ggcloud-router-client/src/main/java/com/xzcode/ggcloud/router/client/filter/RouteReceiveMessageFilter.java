package com.xzcode.ggcloud.router.client.filter;

import com.xzcode.ggcloud.router.client.router.RouterClient;

import xzcode.ggserver.core.common.filter.IBeforeDeserializeFilter;
import xzcode.ggserver.core.common.message.Pack;

public class RouteReceiveMessageFilter implements IBeforeDeserializeFilter{
	
	private RouterClient router;
	
	public RouteReceiveMessageFilter(RouterClient router) {
		super();
		this.router = router;
	}

	@Override
	public boolean doFilter(Pack pack) {
		router.route(pack);
		return true;
	}

}
