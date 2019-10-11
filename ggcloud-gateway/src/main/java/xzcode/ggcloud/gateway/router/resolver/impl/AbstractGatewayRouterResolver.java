package xzcode.ggcloud.gateway.router.resolver.impl;

import java.util.ArrayList;
import java.util.List;

import xzcode.ggcloud.gateway.config.GGGatewayConfig;
import xzcode.ggcloud.gateway.router.resolver.IGGGatewayRouterResolver;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;

public abstract class AbstractGatewayRouterResolver implements IGGGatewayRouterResolver{
	
	protected String routingHost;
	
	protected int port;
	
	protected GGGatewayConfig config;
	
	protected List<GGClient> routingClients = new ArrayList<>();
	
	public AbstractGatewayRouterResolver(GGGatewayConfig config) {
		super();
		this.config = config;
	}
	
	public void newConnection() {
		
		GGClientConfig config = new GGClientConfig();
		config.init();
		GGClient client = new GGClient(config);
		
		//注册连接成功事件
		client.onEvent(GGEvents.ConnectionState.ACTIVE, (e) -> {
			System.out.println("连接成功");
		});
		//注册连接关闭事件
		client.onEvent(GGEvents.ConnectionState.CLOSE, (e) -> {
			System.out.println("连接关闭");
		});
		
		client.connect(getRoutingHost(), getRoutingPort());
		
	}

	public String getRoutingHost() {
		return routingHost;
	}

	public void setRoutingHost(String routingHost) {
		this.routingHost = routingHost;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public GGGatewayConfig getConfig() {
		return config;
	}

	public void setConfig(GGGatewayConfig config) {
		this.config = config;
	}

	public List<GGClient> getRoutingClients() {
		return routingClients;
	}

	public void setRoutingClients(List<GGClient> routingClients) {
		this.routingClients = routingClients;
	}
	
	
	
	
}
