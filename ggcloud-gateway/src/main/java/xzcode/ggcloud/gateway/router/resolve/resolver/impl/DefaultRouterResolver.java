package xzcode.ggcloud.gateway.router.resolve.resolver.impl;

import xzcode.ggcloud.gateway.config.GGGatewayConfig;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterResolver;
import xzcode.ggserver.core.common.message.PackModel;

public class DefaultRouterResolver implements IRouterResolver{
	
	protected String id;
	
	protected String host;
	
	protected int port;
	
	protected GGGatewayConfig config;
	
	public DefaultRouterResolver(GGGatewayConfig config) {
		super();
		this.config = config;
	}

	@Override
	public boolean match(PackModel packModel) {
		return false;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}
	
	

}
