package xzcode.ggcloud.gateway.router.resolve.resolver.impl;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;

public class DefaultRouterService implements IRouterService{
	
	protected String name;
	
	protected String id;
	
	protected String host;
	
	protected int port;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	

}
