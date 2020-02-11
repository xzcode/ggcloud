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
import com.xzcode.ggcloud.router.client.router.service.impl.RouterUsereIdMetadataPackHandler;
import com.xzcode.ggcloud.router.common.constant.RouterServiceCustomDataKeys;
import com.xzcode.ggcloud.router.common.meta.impl.RouterSessionIdMetadataProvider;
import com.xzcode.ggcloud.router.common.meta.impl.RouterSessionIdMetadataResolver;

import io.netty.channel.nio.NioEventLoopGroup;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.common.message.meta.provider.IMetadataProvider;
import xzcode.ggserver.core.common.message.meta.resolver.IMetadataResolver;
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
	protected String routerGroup;
	
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
	 * 服务重连间隔毫秒数
	 */
	private int serviceReconnectDelayMs = 5000;

	/**
	 * 路由服务提供者
	 */
	private IRouterServiceProvider serviceProvider;

	/**
	 * 元数据解析器
	 */
	private IMetadataResolver<?> metadataResolver;
	
	/**
	 * 元数据提供者
	 */
	private IMetadataProvider<?> metadataProvider;
	
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
		
		if (metadataResolver == null) {
			metadataResolver = new RouterSessionIdMetadataResolver(routingServer.getSerializer());
		}
		if (metadataProvider == null) {
			metadataProvider = new RouterSessionIdMetadataProvider();
		}

		if (packHandler == null) {
			packHandler = new RouterUsereIdMetadataPackHandler(this);
		}
		
		this.routingServer.addBeforeDeserializeFilter(new RouteReceiveMessageFilter(this));
		
		if (routerGroup == null) {
			routerGroup = UUID.randomUUID().toString();
		}

		DiscoveryClient discoveryClient = getDiscoveryClient();
		if (discoveryClient != null) {
			discoveryClient.getConfig().addCustomData(RouterServiceCustomDataKeys.ROUTER_SERVICE_GROUP, getRouterGroup());
			serviceProvider = new DefaultDiscoveryServicePorvider(this);
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

	public int getServiceReconnectDelayMs() {
		return serviceReconnectDelayMs;
	}

	public void setServiceReconnectDelayMs(int serviceReconnectDelayMs) {
		this.serviceReconnectDelayMs = serviceReconnectDelayMs;
	}

	public IMetadataResolver<?> getMetadataResolver() {
		return metadataResolver;
	}

	public void setMetadataResolver(IMetadataResolver<?> metadataResolver) {
		this.metadataResolver = metadataResolver;
	}

	public IMetadataProvider<?> getMetadataProvider() {
		return metadataProvider;
	}

	public void setMetadataProvider(IMetadataProvider<?> metadataProvider) {
		this.metadataProvider = metadataProvider;
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

	public String getRouterGroup() {
		return routerGroup;
	}

	public void setRouterGroup(String routerGroupId) {
		this.routerGroup = routerGroupId;
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
