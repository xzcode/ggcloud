package com.xzcode.ggcloud.eventbus.client.config;

import com.xzcode.ggcloud.session.group.common.constant.GGSesssionGroupConstant;
import com.xzcode.ggcloud.session.group.common.util.UuidUtil;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.common.executor.DefaultTaskExecutor;
import xzcode.ggserver.core.common.executor.ITaskExecutor;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.group.manager.GGSessionGroupManager;

/**
 * 配置
 * 
 * 
 * @author zai 2019-10-04 17:23:47
 */
public class SessionGroupClientConfig {

	// sessionClient对象
	protected GGClient sessionClient;

	protected GGSessionGroupManager sessionGroupManager;

	// 服务器域名
	protected String serverHost = "localhost";

	// 服务器端口
	protected int serverPort = GGSesssionGroupConstant.DEFAULT_SERVER_PORT;

	// 连接数
	protected int connectionSize = 4;

	protected boolean printPingPongInfo = false;

	// 会话组id
	protected String sessionGroupId = UuidUtil.newId();

	// 任务执行器
	protected ITaskExecutor taskExecutor = new DefaultTaskExecutor("discovery-client-", 1);

	// GGSession对象
	protected GGSession session;

	// 是否打印pingpong包信息
	protected boolean pingPongEnabled = false;

	// 客户端汇报超时时间(秒)
	protected long clientReportInterval = 30L * 1000L;

	// 重连间隔-秒
	protected long reconnectInterval = 5L * 1000L;

	// 尝试重新注册周期，ms
	protected long tryRegisterInterval = 10L * 1000L;

	// 验证token
	protected String authToken = GGSesssionGroupConstant.DEFAULT_AUTH_TOKEN;

	public long getClientReportInterval() {
		return clientReportInterval;
	}

	public void setClientReportInterval(long clientReportInterval) {
		this.clientReportInterval = clientReportInterval;
	}

	public long getReconnectInterval() {
		return reconnectInterval;
	}

	public void setReconnectInterval(long reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
	}

	public long getTryRegisterInterval() {
		return tryRegisterInterval;
	}

	public void setTryRegisterInterval(long tryRegisterInterval) {
		this.tryRegisterInterval = tryRegisterInterval;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public void setSession(GGSession session) {
		this.session = session;
	}

	public GGSession getSession() {
		return session;
	}

	public boolean isPingPongEnabled() {
		return pingPongEnabled;
	}

	public void setPingPongEnabled(boolean pingPongEnabled) {
		this.pingPongEnabled = pingPongEnabled;
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

}
