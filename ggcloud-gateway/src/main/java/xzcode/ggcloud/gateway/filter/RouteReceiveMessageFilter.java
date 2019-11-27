package xzcode.ggcloud.gateway.filter;

import xzcode.ggcloud.gateway.router.IGatewayRouter;
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
	public boolean doFilter(GGSession session, Pack pack) {
		router.route(session, pack);
		return true;
	}

}