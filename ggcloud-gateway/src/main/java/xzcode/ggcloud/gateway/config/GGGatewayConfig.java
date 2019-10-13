package xzcode.ggcloud.gateway.config;

import java.nio.charset.Charset;

import io.netty.channel.nio.NioEventLoopGroup;

public class GGGatewayConfig {
	
	/**
	 * 公共事件循环组
	 */
	protected NioEventLoopGroup eventLoopGroup;
	
	/**
	 * 字符串编码格式
	 */
	private Charset charset = Charset.forName("utf-8");
	
	/**
	 * 默认路由连接数
	 */
	private int routingConnectionNum = 16;

	public Charset getCharset() {
		return charset;
	}
	
	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public int getRoutingConnectionNum() {
		return routingConnectionNum;
	}
	
	public void setRoutingConnectionNum(int routingConnectionNum) {
		this.routingConnectionNum = routingConnectionNum;
	}
	
	
	
	
}
