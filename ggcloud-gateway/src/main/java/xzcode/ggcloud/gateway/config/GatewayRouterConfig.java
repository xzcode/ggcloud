package xzcode.ggcloud.gateway.config;

import java.nio.charset.Charset;

import io.netty.channel.nio.NioEventLoopGroup;
import nonapi.io.github.classgraph.concurrency.SimpleThreadFactory;
import xzcode.ggcloud.gateway.router.service.IRouterServiceProvider;
import xzcode.ggcloud.gateway.router.service.impl.DefaultServicePorvider;
import xzcode.ggserver.core.server.GGServer;

/**
 * 网关路由配置
 * 
 * @author zai
 * 2019-11-07 14:35:07
 */
public class GatewayRouterConfig {
	
	/**
	 * 公共事件循环组
	 */
	protected NioEventLoopGroup executor;
	
	/**
	 * 线程执行器最大线程数
	 */
	protected int executorThreads = 32;
	
	/**
	 * 字符串编码格式
	 */
	private Charset charset = Charset.forName("utf-8");
	
	/**
	 * 路由服务提供者
	 */
	private IRouterServiceProvider serviceProvider;
	
	/**
	 * 消息将被路由的服务器对象
	 */
	private GGServer routingServer;
	
	/**
	 * 不参与路由的action匹配正则表达式
	 */
	private String[] excludedRoutingActionRegex;
	
	/**
	 * 对每个服务生成的连接数
	 */
	private int serviceConnectionSize = 3;
	
	/**
	 * 服务重连间隔毫秒数
	 */
	private int serviceReconnectDelayMs = 5000;
	
	
	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-10-22 18:27:01
	 */
	public void init() {
		if (executor == null) {
			executor = new NioEventLoopGroup(executorThreads, new SimpleThreadFactory("router-event-loop-", false));
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

	public GGServer getRoutingServer() {
		return routingServer;
	}

	public void setRoutingServer(GGServer routingServer) {
		this.routingServer = routingServer;
	}

	public String[] getExcludedRoutingActionRegex() {
		return excludedRoutingActionRegex;
	}

	public void setExcludedRoutingActionRegex(String[] excludedRoutingActionRegex) {
		this.excludedRoutingActionRegex = excludedRoutingActionRegex;
	}

	public int getServiceConnectionSize() {
		return serviceConnectionSize;
	}

	public void setServiceConnectionSize(int serviceConnectionSize) {
		this.serviceConnectionSize = serviceConnectionSize;
	}

	public int getServiceReconnectDelayMs() {
		return serviceReconnectDelayMs;
	}

	public void setServiceReconnectDelayMs(int serviceReconnectDelayMs) {
		this.serviceReconnectDelayMs = serviceReconnectDelayMs;
	}

	
}
