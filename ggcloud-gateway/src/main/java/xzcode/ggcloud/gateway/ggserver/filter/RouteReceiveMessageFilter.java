package xzcode.ggcloud.gateway.ggserver.filter;

import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.message.filter.GGBeforeDeserializeFilter;
import xzcode.ggserver.core.common.session.GGSessionUtil;

/**
 * 消息路由
 * 
 * @author zai
 * 2019-10-09 19:57:21
 */
public class RouteReceiveMessageFilter implements GGBeforeDeserializeFilter{
	
	private IGatewayRouter router;
	
	@Override
	public boolean doFilter(PackModel pack) {
		return router.route(pack, GGSessionUtil.getSession());
	}
	
	public void setRouter(IGatewayRouter router) {
		this.router = router;
	}

}
