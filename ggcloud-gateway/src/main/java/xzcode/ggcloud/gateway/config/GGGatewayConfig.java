package xzcode.ggcloud.gateway.config;

public class GGGatewayConfig {
	
	/**
	 * 字符串编码格式
	 */
	private String charset = "utf-8";
	
	/**
	 * 默认路由连接数
	 */
	private int defaultRoutingConnectionNum = 16;

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getDefaultRoutingConnectionNum() {
		return defaultRoutingConnectionNum;
	}

	public void setDefaultRoutingConnectionNum(int defaultRoutingConnectionNum) {
		this.defaultRoutingConnectionNum = defaultRoutingConnectionNum;
	}
	
	
	
	
}
