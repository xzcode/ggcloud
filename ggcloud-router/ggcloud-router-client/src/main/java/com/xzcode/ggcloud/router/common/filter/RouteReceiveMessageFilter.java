package com.xzcode.ggcloud.router.common.filter;

import com.xzcode.ggcloud.router.common.router.IGatewayRouter;

import xzcode.ggserver.core.common.filter.IBeforeDeserializeFilter;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

public class RouteReceiveMessageFilter implements IBeforeDeserializeFilter{
	
	private IGatewayRouter router;
	
	public RouteReceiveMessageFilter(IGatewayRouter router) {
		super();
		this.router = router;
	}

	@Override
	public boolean doFilter(Pack pack) {
		router.route(pack);
		return true;
	}

}
