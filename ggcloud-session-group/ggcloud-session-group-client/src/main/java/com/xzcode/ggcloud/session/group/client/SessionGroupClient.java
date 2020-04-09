package com.xzcode.ggcloud.session.group.client;

import com.xzcode.ggcloud.session.group.client.config.SessionGroupClientConfig;
import com.xzcode.ggcloud.session.group.client.events.ConnCloseEventListener;
import com.xzcode.ggcloud.session.group.client.events.ConnOpenEventListener;
import com.xzcode.ggcloud.session.group.client.handler.AnthRespHandler;
import com.xzcode.ggcloud.session.group.client.handler.DataTransferRespHandler;
import com.xzcode.ggcloud.session.group.client.handler.SessionGroupRegisterRespHandler;
import com.xzcode.ggcloud.session.group.common.group.manager.GGSessionGroupManager;
import com.xzcode.ggcloud.session.group.common.message.req.DataTransferReq;
import com.xzcode.ggcloud.session.group.common.message.resp.AuthResp;
import com.xzcode.ggcloud.session.group.common.message.resp.DataTransferResp;
import com.xzcode.ggcloud.session.group.common.message.resp.SessionGroupRegisterResp;
import com.xzcode.ggcloud.session.group.common.session.SessionGroupSessionFactory;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.executor.DefaultTaskExecutor;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 会话组客户端
 *
 * @author zai 2020-04-08 11:47:15
 */
public class SessionGroupClient {

	private SessionGroupClientConfig config;

	public SessionGroupClient(SessionGroupClientConfig config) {
		this.config = config;
		config.setSessionGroupClient(this);
		init();
	}
	
	
	private void init() {
		
		if (this.config.getTaskExecutor() == null) {
			this.config.setTaskExecutor(new DefaultTaskExecutor("gg-group-cli-", this.config.getWorkThreadSize()));
		}
		
		GGClientConfig sessionClientConfig = new GGClientConfig();

		sessionClientConfig.setPingPongEnabled(true);
		sessionClientConfig.setPrintPingPongInfo(this.config.isPrintPingPongInfo());
		sessionClientConfig.setTaskExecutor(this.config.getTaskExecutor());
		sessionClientConfig.setProtocolType(ProtocolTypeConstants.TCP);
		sessionClientConfig.setSessionFactory(new SessionGroupSessionFactory(sessionClientConfig));
		sessionClientConfig.init();

		GGSessionGroupManager sessionGroupManager = new GGSessionGroupManager(sessionClientConfig);
		sessionGroupManager.addSession(this.config.getSessionGroupId(), null);
		sessionGroupManager.setDefaultGroup(this.config.getSessionGroupId());
		this.config.setSessionGroupManager(sessionGroupManager);

		GGClient sessionClient = new GGClient(sessionClientConfig);
		this.config.setSessionClient(sessionClient);


		sessionClient.onMessage(AuthResp.ACTION_ID, new AnthRespHandler(this.config));
		sessionClient.onMessage(SessionGroupRegisterResp.ACTION_ID, new SessionGroupRegisterRespHandler(this.config));
		sessionClient.onMessage(DataTransferResp.ACTION_ID, new DataTransferRespHandler(this.config));

		sessionClient.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(this.config));
		sessionClient.addEventListener(GGEvents.Connection.OPENED, new ConnOpenEventListener(this.config));
		
		
		if (this.config.isEnableServiceClient() && this.config.getServiceClient() == null) {
			
			GGClientConfig serviceClientConfig = new GGClientConfig();
			serviceClientConfig.setTaskExecutor(config.getTaskExecutor());
			serviceClientConfig.init();
			
			GGClient serviceClient = new GGClient(serviceClientConfig);
			this.config.setServiceClient(serviceClient);
			
		}
	}

	/**
	 * 启动客户端
	 *
	 * @author zai 2020-04-08 11:47:42
	 */
	public void start() {
		this.connect();
	}

	/**
	 * 发送消息
	 *
	 * @param message
	 * @author zai
	 * 2020-04-08 16:14:13
	 */
	public void transferData(DataTransferReq message) {
		
		GGSessionGroupManager sessionGroupManager = this.config.getSessionGroupManager();
		/*
		 * GGSessionGroup defaultGroup = sessionGroupManager.getDefaultGroup(); Pack
		 * pack = defaultGroup.makePack(new MessageData<>(null, message.getActionId(),
		 * message)); DataTransferReq req = new DataTransferReq();
		 * req.setAction(pack.getAction()); req.setMessage(pack.getMessage());
		 */
		sessionGroupManager.sendToDefaultGroupRandomOne(message);
		sessionGroupManager.sendToRandomOne(this.config.getSessionGroupId(), message);
	}

	/**
	 * 进行连接操作
	 *
	 * @author zai 2020-04-08 11:46:06
	 */
	private void connect() {
		// 根据设置的连接数进行连接初始化
		int connectionSize = config.getConnectionSize();
		for (int i = 0; i < connectionSize; i++) {
			connectOne(config.getServerHost(), config.getServerPort());
		}
	}

	/**
	 * 进行一次连接
	 *
	 * @param host
	 * @param port
	 * @author zai 2020-04-08 11:45:53
	 */
	public void connectOne(String host, int port) {
		GGClient ggclient = config.getSessionClient();
		ggclient.connect(host, port).addListener(f -> {
			if (!f.isSuccess()) {
				// 连接失败，进行进行重连操作
				GGLoggerUtil.getLogger(this).warn("SessionGroupClient Connect Server[{}:{}] Failed!", host, port);
				ggclient.schedule(config.getReconnectInterval(), () -> {
					connectOne(host, port);
				});
				return;
			}
			GGLoggerUtil.getLogger(this).warn("SessionGroupClient Connect Server[{}:{}] Successfully!", host, port);
		});
	}

}
