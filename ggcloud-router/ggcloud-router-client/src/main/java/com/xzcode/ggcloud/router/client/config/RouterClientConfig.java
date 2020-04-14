package com.xzcode.ggcloud.router.client.config;

import java.nio.charset.Charset;
import java.util.UUID;

import com.xzcode.ggcloud.discovery.client.DiscoveryClient;
import com.xzcode.ggcloud.router.client.RouterClient;
import com.xzcode.ggcloud.router.client.filter.RouteReceiveMessageFilter;
import com.xzcode.ggcloud.router.client.router.service.IRouterPackHandler;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceProvider;
import com.xzcode.ggcloud.router.client.router.service.impl.DefaultDiscoveryServicePorvider;
import com.xzcode.ggcloud.router.client.router.service.impl.DefaultServicePorvider;
import com.xzcode.ggcloud.router.common.constant.RouterServiceCustomDataKeys;

import io.netty.channel.nio.NioEventLoopGroup;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.impl.GGServer;

/**
 * 网关路由配置
 * 
 * @author zai
 * 2019-11-07 14:35:07
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
	
	/**
	 * 路由客户端对象
	 */
	protected RouterClient routerClient;
	
	/**
	 * 路由客户端最大连接数
	 */
	protected int routerClientChannelPoolMaxSize = 2;
	/**
	 * 公共事件循环组
	 */
	protected NioEventLoopGroup executor;
	
	/**
	 * 线程执行器最大线程数
	 */
	protected int executorThreads = 0;
	
	/**
	 * 字符串编码格式
	 */
	private Charset charset = Charset.forName("utf-8");
	
	
	/**
	 * 消息将被路由的服务器对象
	 */
	private IGGServer routingServer;
	
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
	
	
	public RouterClientConfig(IGGServer routingServer) {
		if (routingServer == null) {
			throw new NullPointerException("Parameter 'routingServer' cannot be null!!");
		}
		this.routingServer = routingServer;
		
	}

	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-10-22 18:27:01
	 */
	public void init() {
		
		if (executor == null) {
			executor = new NioEventLoopGroup(executorThreads, new GGThreadFactory("router-executor-", false));
		}
		

		
		this.routingServer.addBeforeDeserializeFilter(new RouteReceiveMessageFilter(this));
		
		if (routerGroupId == null) {
			routerGroupId = UUID.randomUUID().toString();
		}

		if (this.discoveryClient != null) {
			this.discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_GROUP, getRouterGroupId());
			setServiceProvider(new DefaultDiscoveryServicePorvider(this));
		}
		
		if (serviceProvider == null) {
			serviceProvider = new DefaultServicePorvider(this);
		}
		
	}

	public NioEventLoopGroup getExecutor() {
		return executor;
	}

	public void setExecutor(NioEventLoopGroup executor) {
		this.executor = executor;
	}

	public int getExecutorThreads() {
		return executorThreads;
	}

	public void setExecutorThreads(int executorThreads) {
		this.executorThreads = executorThreads;
	}


	public Charset getCharset() {
		return charset;
	}
	
	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public IRouterServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(IRouterServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public IGGServer getRoutingServer() {
		return routingServer;
	}

	public void setRoutingServer(GGServer routingServer) {
		this.routingServer = routingServer;
	}

	public String[] getExcludedActionId() {
		return excludedActionId;
	}

	public void setExcludedActionId(String[] excludedRoutingActionRegex) {
		this.excludedActionId = excludedRoutingActionRegex;
	}

	public void setRoutingServer(IGGServer routingServer) {
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
	
	
	public int getRouterClientChannelPoolMaxSize() {
		return routerClientChannelPoolMaxSize;
	}
	
	public void setRouterClientChannelPoolMaxSize(int routerClientChannelPoolMaxSize) {
		this.routerClientChannelPoolMaxSize = routerClientChannelPoolMaxSize;
	}
	
	public DiscoveryClient getDiscoveryClient() {
		return discoveryClient;
	}
	
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
}
