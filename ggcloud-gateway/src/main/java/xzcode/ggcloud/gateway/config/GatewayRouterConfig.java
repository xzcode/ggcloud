package xzcode.ggcloud.gateway.config;

import java.nio.charset.Charset;

import io.netty.channel.nio.NioEventLoopGroup;
import nonapi.io.github.classgraph.concurrency.SimpleThreadFactory;
import xzcode.ggcloud.gateway.router.resolve.provider.IRouterServiceProvider;
import xzcode.ggcloud.gateway.router.resolve.provider.impl.DefaultServicePorvider;
import xzcode.ggserver.core.server.GGServer;

public class GatewayRouterConfig {
	
	/**
	 * 公共事件循环组
	 */
	protected NioEventLoopGroup executor;
	
	/**
	 * 公共事件循环组
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
	 * 路由服务器
	 */
	private GGServer routingServer;
	
	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-10-22 18:27:01
	 */
	public void init() {
		if (executor == null) {
			executor = new NioEventLoopGroup(executorThreads, new SimpleThreadFactory("ggc-router-", false));
		}
		if (serviceProvider == null) {
			serviceProvider = new DefaultServicePorvider();
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
	
	
	
	
}
