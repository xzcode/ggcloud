package xzcode.ggcloud.gateway.ggserver.filter;

import xzcode.ggserver.core.common.filter.GGBeforeDeserializeFilter;
import xzcode.ggserver.core.common.message.Pack;

public class RouteReceiveMessageFilter implements GGBeforeDeserializeFilter{
	
	
	@Override
	public boolean doFilter(Pack pack) {
		return true;
	}

}
