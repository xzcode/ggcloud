package xzcode.ggcloud.gateway.router.resolve.resolver.impl;

import java.nio.charset.Charset;

import xzcode.ggcloud.gateway.config.GGGatewayConfig;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterResolver;
import xzcode.ggserver.core.common.message.PackModel;

public class DefaultRouterResolver implements IRouterResolver{
	
	protected String id;
	
	//action前缀
	protected String actionPrefix;
	
	protected String host;
	
	protected int port;
	
	protected GGGatewayConfig config;
	
	public DefaultRouterResolver(GGGatewayConfig config) {
		super();
		this.config = config;
	}

	@Override
	public boolean match(PackModel packModel) {
		String action = new String(packModel.getAction(), config.getCharset());
		return action.startsWith(actionPrefix);
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
