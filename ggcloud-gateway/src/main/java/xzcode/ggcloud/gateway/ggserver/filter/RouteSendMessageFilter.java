package xzcode.ggcloud.gateway.ggserver.filter;

import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggserver.core.common.filter.IBeforeDeserializeFilter;
import xzcode.ggserver.core.common.filter.ISendFilter;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

public class RouteSendMessageFilter implements ISendFilter{
	
	private IGatewayRouter router;
	
	public RouteSendMessageFilter(IGatewayRouter router) {
		super();
		this.router = router;
	}

	@Override
	public boolean doFilter(GGSession session, Pack pack) {
		router.route(session, pack);
		return true;
	}

}
