package com.xzcode.ggcloud.session.group.client.config;

import com.xzcode.ggcloud.session.group.client.SessionGroupClient;
import com.xzcode.ggcloud.session.group.common.constant.GGSesssionGroupConstant;
import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.common.executor.ITaskExecutor;
import xzcode.ggserver.core.common.utils.RandomIdUtil;

/**
 * 配置
 * 
 * 
 * @author zai 2019-10-04 17:23:47
 */
public class SessionGroupClientConfig {
	
	//会话组客户端
	protected SessionGroupClient sessionGroupClient;

	// 会话客户端
	protected GGClient sessionClient;
	
	//业务客户端
	protected GGClient serviceClient;
	
	//开启业务客户端
	protected boolean enableServiceClient;

	protected GGSessionGroupManager sessionGroupManager;

	// 服务器域名
	protected String serverHost = "localhost";

	// 服务器端口
	protected int serverPort = GGSesssionGroupConstant.DEFAULT_SERVER_PORT;

	//工作线程数
	protected int workThreadSize = 8;
	
	
	// 连接数
	protected int connectionSize = 8;
	
	//重连周期 毫秒
	protected long reconnectInterval = 10L * 1000L;

	protected boolean printPingPongInfo = false;

	// 会话组id
	protected String sessionGroupId = RandomIdUtil.newRandomStringId24();

	// 任务执行器
	protected ITaskExecutor taskExecutor;


	// 验证token
	protected String authToken = GGSesssionGroupConstant.DEFAULT_AUTH_TOKEN;


	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}


	public boolean isPrintPingPongInfo() {
		return printPingPongInfo;
	}

	public void setPrintPingPongInfo(boolean printPingPongInfo) {
		this.printPingPongInfo = printPingPongInfo;
	}

	public String getSessionGroupId() {
		return sessionGroupId;
	}

	public void setSessionGroupId(String sessionGroupId) {
		this.sessionGroupId = sessionGroupId;
	}

	public ITaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ITaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void setSessionClient(GGClient sessionClient) {
		this.sessionClient = sessionClient;
	}

	public GGClient getSessionClient() {
		return sessionClient;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public int getConnectionSize() {
		return connectionSize;
	}

	public void setConnectionSize(int connectionSize) {
		this.connectionSize = connectionSize;
	}
	
	public void setSessionGroupManager(GGSessionGroupManager sessionGroupManager) {
		this.sessionGroupManager = sessionGroupManager;
	}
	
	public GGSessionGroupManager getSessionGroupManager() {
		return sessionGroupManager;
	}
	
	public GGClient getServiceClient() {
		return serviceClient;
	}
	
	public void setServiceClient(GGClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	public boolean isEnableServiceClient() {
		return enableServiceClient;
	}
	
	public void setEnableServiceClient(boolean enableServiceClient) {
		this.enableServiceClient = enableServiceClient;
	}
	
	public void setSessionGroupClient(SessionGroupClient sessionGroupClient) {
		this.sessionGroupClient = sessionGroupClient;
	}
	
	public SessionGroupClient getSessionGroupClient() {
		return sessionGroupClient;
	}
	
	public int getWorkThreadSize() {
		return workThreadSize;
	}
	
	public void setWorkThreadSize(int workThreadSize) {
		this.workThreadSize = workThreadSize;
	}
	
	public long getReconnectInterval() {
		return reconnectInterval;
	}
	
	public void setReconnectInterval(long reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
	}
	

}
