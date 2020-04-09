package com.xzcode.ggcloud.session.group.client.handler;

import com.xzcode.ggcloud.session.group.client.config.SessionGroupClientConfig;
import com.xzcode.ggcloud.session.group.common.message.resp.DataTransferResp;

import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.request.action.MessageDataHandler;
import xzcode.ggserver.core.common.message.request.task.MessageDataTask;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.manager.ISessionManager;

/**
 * 数据传输推送
 *
 * @author zai
 * 2020-04-09 15:03:50
 */
public class DataTransferRespHandler implements MessageDataHandler<DataTransferResp> {

	private SessionGroupClientConfig config;

	public DataTransferRespHandler(SessionGroupClientConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void handle(MessageData<DataTransferResp> messageData) {
		
		DataTransferResp resp = messageData.getMessage();
		
		//开启服务客户端
		if (this.config.isEnableServiceClient()) {
			String tranferSessionId = resp.getTranferSessionId();
			GGClient serviceClient = this.config.getServiceClient();
			GGClientConfig serviceClientConfig = serviceClient.getConfig();
			ISessionManager sessionManager = serviceClient.getSessionManager();
			GGSession session = sessionManager.getSession(tranferSessionId);
			//提交任务到业务客户端
			Pack pack = new Pack(session, resp.getAction(), resp.getMessage());
			serviceClient.submitTask(new MessageDataTask(pack , serviceClientConfig));
			
		}
		
	}

}
