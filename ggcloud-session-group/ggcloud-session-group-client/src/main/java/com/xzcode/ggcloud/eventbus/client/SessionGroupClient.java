package com.xzcode.ggcloud.eventbus.client;

import com.xzcode.ggcloud.eventbus.client.config.SessionGroupClientConfig;
import com.xzcode.ggcloud.eventbus.client.events.ConnCloseEventListener;
import com.xzcode.ggcloud.eventbus.client.events.ConnOpenEventListener;
import com.xzcode.ggcloud.eventbus.client.handler.AnthRespHandler;
import com.xzcode.ggcloud.eventbus.client.handler.SessionGroupRegisterRespHandler;
import com.xzcode.ggcloud.session.group.common.message.resp.AuthResp;
import com.xzcode.ggcloud.session.group.common.message.resp.SessionGroupRegisterResp;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.constant.ProtocolTypeConstants;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 会话组客户端
 *
 * @author zai
 * 2020-04-08 11:47:15
 */
public class SessionGroupClient {
	
	private SessionGroupClientConfig config;
	
	public SessionGroupClient(SessionGroupClientConfig config) {
		this.config = config;
	}

	/**
	 * 启动客户端
	 *
	 * @author zai
	 * 2020-04-08 11:47:42
	 */
	public void start() {
		GGClientConfig sessionClientConfig = new GGClientConfig();
		
		sessionClientConfig.setPingPongEnabled(true);
		sessionClientConfig.setPrintPingPongInfo(config.isPrintPingPongInfo());
		sessionClientConfig.setTaskExecutor(config.getTaskExecutor());
		sessionClientConfig.setProtocolType(ProtocolTypeConstants.TCP);
		sessionClientConfig.init();
		
		GGClient sessionClient = new GGClient(sessionClientConfig);
		config.setSessionClient(sessionClient);
		
		sessionClient.onMessage(AuthResp.ACTION_ID, new AnthRespHandler(config));
		sessionClient.onMessage(SessionGroupRegisterResp.ACTION_ID, new SessionGroupRegisterRespHandler(config));
		
		
		sessionClient.addEventListener(GGEvents.Connection.CLOSED, new ConnCloseEventListener(config));
		sessionClient.addEventListener(GGEvents.Connection.OPENED, new ConnOpenEventListener(config));
		
		
		this.connect();
		
		
	}
	
	
	/**
	 * 进行连接操作
	 *
	 * @author zai
	 * 2020-04-08 11:46:06
	 */
	private void connect() {
		//根据设置的连接数进行连接初始化
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
	 * @author zai
	 * 2020-04-08 11:45:53
	 */
	private void connectOne(String host, int port) {
		GGClient ggclient = config.getSessionClient();
		ggclient.connect(host, port).addListener(f -> {
			if (!f.isSuccess()) {
				//连接失败，进行进行重连操作
				GGLoggerUtil.getLogger(this).info("Discovery Client Connect Server[{}:{}] Failed!",host, port);
				ggclient.schedule(config.getTryRegisterInterval(), () -> {
					connectOne(host, port);
				});
				return;
			}
			GGLoggerUtil.getLogger(this).info("Discovery Client Connect Server[{}:{}] Successfully!",host, port);
		});
	}
	

}
