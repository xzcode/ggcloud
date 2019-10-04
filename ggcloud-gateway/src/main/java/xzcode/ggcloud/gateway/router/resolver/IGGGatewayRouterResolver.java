package xzcode.ggcloud.gateway.router.resolver;

public interface IGGGatewayRouterResolver {
	
	void resolve(ResolvePack pack);
	
	String getRoutingHost();
	
	int getRoutingPort();
	
	boolean match(String action);
	
}
