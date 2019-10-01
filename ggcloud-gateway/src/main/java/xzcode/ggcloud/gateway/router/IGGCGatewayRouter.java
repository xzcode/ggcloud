package xzcode.ggcloud.gateway.router;

import xzcode.ggserver.core.message.receive.Request;

public interface IGGCGatewayRouter {
	
	
	void routeMessage(Request request);
	
}
