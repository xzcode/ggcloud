package com.xzcode.ggcloud.detector.server.config;

/**
 * 配置
 * 
 * 
 * @author zai
 * 2019-10-04 17:23:47
 */
public class GGCDetectorConfig {
	
	//服务端口
	private int port = 9394;
	
	//认证token
	private String authToken;
	
	//客户端汇报超时时间(秒)
	private long clientReportTimeout = 30L;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public long getClientReportTimeout() {
		return clientReportTimeout;
	}

	public void setClientReportTimeout(long clientReportTimeout) {
		this.clientReportTimeout = clientReportTimeout;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	

}
