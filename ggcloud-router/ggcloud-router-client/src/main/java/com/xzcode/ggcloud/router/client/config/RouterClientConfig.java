package com.xzcode.ggcloud.router.client.config;

import java.util.UUID;

import com.xzcode.ggcloud.discovery.client.DiscoveryClient;
import com.xzcode.ggcloud.router.client.RouterClient;
import com.xzcode.ggcloud.router.client.filter.RouteReceiveMessageFilter;
import com.xzcode.ggcloud.router.client.router.service.IRouterPackHandler;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceProvider;
import com.xzcode.ggcloud.router.client.router.service.impl.DefaultDiscoveryServicePorvider;
import com.xzcode.ggcloud.router.client.router.service.impl.DefaultServicePorvider;
import com.xzcode.ggcloud.router.common.constant.GGRouterConstant;
import com.xzcode.ggcloud.router.common.constant.RouterServiceCustomDataKeys;
import com.xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import com.xzcode.ggserver.core.server.GGServer;
import com.xzcode.ggserver.core.server.impl.GGDefaultServer;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * 路由客户端配置
 *
 * @author zai
 * 2020-04-14 17:18:45
 */
public class RouterClientConfig {

	/**
	 * 路由组id
	 */
	protected String routerGroupId;

	/**
	 * 注册中心客户端
	 */
	protected DiscoveryClient discoveryClient;

	// routerClient对象
	protected RouterClient routerClient;


	// 是否输出底层ping pong信息
	protected boolean printPingPongInfo = false;
	
	// 是否输出路由调试信息
	protected boolean printRouterInfo = false;

	// 工作线程数
	protected int workThreadSize = 8;

	// 连接数
	protected int connectionSize = 4;


	// 服务端地址
	protected String serverHost = GGRouterConstant.DEFAULT_SERVER_HOST;

	// 服务端端口
	protected int port = GGRouterConstant.DEFAULT_SERVER_PORT;

	// 验证token
	protected String authToken = GGRouterConstant.DEFAULT_AUTH_TOKEN;

	/**
	 * 消息将被路由的服务器对象
	 */
	private GGServer routingServer;

	/**
	 * 不参与路由的actionid
	 * 
	 */
	private String[] excludedActionId;

	/**
	 * 路由服务提供者
	 */
	private IRouterServiceProvider serviceProvider;

	/**
	 * 路由包处理器
	 */
	private IRouterPackHandler packHandler;
	
	/**
	 * 共享的线程组
	 */
	private EventLoopGroup sharedEventLoopGroup;

	public RouterClientConfig(GGServer routingServer) {
		if (routingServer == null) {
			throw new NullPointerException("Parameter 'routingServer' cannot be null!!");
		}
		this.routingServer = routingServer;

	}

	/**
	 * 初始化
	 * 
	 * @author zai 2019-10-22 18:27:01
	 */
	public void init() {

		if (this.sharedEventLoopGroup == null) {
			this.sharedEventLoopGroup = new NioEventLoopGroup(workThreadSize, new GGThreadFactory("gg-router-", false));
		}
		
		this.routingServer.addBeforeDeserializeFilter(new RouteReceiveMessageFilter(this));

		if (routerGroupId == null) {
			routerGroupId = UUID.randomUUID().toString();
		}

		if (this.discoveryClient != null) {
			this.discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_GROUP,getRouterGroupId());
			setServiceProvider(new DefaultDiscoveryServicePorvider(this));
		}

		if (serviceProvider == null) {
			serviceProvider = new DefaultServicePorvider(this);
		}

	}

	public IRouterServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(IRouterServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public GGServer getRoutingServer() {
		return routingServer;
	}

	public void setRoutingServer(GGDefaultServer routingServer) {
		this.routingServer = routingServer;
	}

	public String[] getExcludedActionId() {
		return excludedActionId;
	}

	public void setExcludedActionId(String[] excludedRoutingActionRegex) {
		this.excludedActionId = excludedRoutingActionRegex;
	}

	public void setRoutingServer(GGServer routingServer) {
		this.routingServer = routingServer;
	}

	public IRouterPackHandler getPackHandler() {
		return packHandler;
	}

	public void setPackHandler(IRouterPackHandler packHandler) {
		this.packHandler = packHandler;
	}

	public RouterClient getRouterClient() {
		return routerClient;
	}

	public void setRouterClient(RouterClient routerClient) {
		this.routerClient = routerClient;
	}

	public String getRouterGroupId() {
		return routerGroupId;
	}

	public void setRouterGroupId(String routerGroupId) {
		this.routerGroupId = routerGroupId;
	}


	public DiscoveryClient getDiscoveryClient() {
		return discoveryClient;
	}

	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	public boolean isPrintPingPongInfo() {
		return printPingPongInfo;
	}

	public void setPrintPingPongInfo(boolean printPingPongInfo) {
		this.printPingPongInfo = printPingPongInfo;
	}

	public int getWorkThreadSize() {
		return workThreadSize;
	}

	public void setWorkThreadSize(int workThreadSize) {
		this.workThreadSize = workThreadSize;
	}

	public int getConnectionSize() {
		return connectionSize;
	}

	public void setConnectionSize(int connectionSize) {
		this.connectionSize = connectionSize;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public EventLoopGroup getSharedEventLoopGroup() {
		return sharedEventLoopGroup;
	}

	public void setSharedEventLoopGroup(EventLoopGroup sharedEventLoopGroup) {
		this.sharedEventLoopGroup = sharedEventLoopGroup;
	}

	public boolean isPrintRouterInfo() {
		return printRouterInfo;
	}

	public void setPrintRouterInfo(boolean printRouterInfo) {
		this.printRouterInfo = printRouterInfo;
	}
	
	

}
