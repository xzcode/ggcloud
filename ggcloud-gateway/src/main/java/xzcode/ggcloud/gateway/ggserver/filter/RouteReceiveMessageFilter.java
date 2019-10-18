package xzcode.ggcloud.gateway.ggserver.filter;

import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.message.filter.GGBeforeDeserializeFilter;

public class RouteReceiveMessageFilter implements GGBeforeDeserializeFilter{
	
	
	@Override
	public boolean doFilter(PackModel pack) {
		return true;
	}

}
