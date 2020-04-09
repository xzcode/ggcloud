package com.xzcode.ggcloud.session.group.server.handler;

import com.xzcode.ggcloud.session.group.common.message.req.DataTransferReq;
import com.xzcode.ggcloud.session.group.common.util.UuidUtil;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;
import com.xzcode.ggcloud.session.group.server.session.ServiceServerSession;

import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.message.request.task.RequestMessageTask;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.manager.ISessionManager;
import xzcode.ggserver.core.common.utils.RandomIdUtil;
import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;

/**
 * 客户端认证请求
 *
 * @author zai 2020-04-07 10:57:11
 */
public class DataTransferReqHandler implements IRequestMessageHandler<DataTransferReq> {

	private SessionGroupServerConfig config;

	public DataTransferReqHandler(SessionGroupServerConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void handle(MessageData<DataTransferReq> request) {
		DataTransferReq req = request.getMessage();
		
		IGGServer serviceServer = config.getServiceServer();
		GGServerConfig serviceServerConfig = serviceServer.getConfig();
		
		//获取传递的sessionid
		String tranferSessionId = req.getTranferSessionId();
		
		
		//创建session
		if (tranferSessionId == null) {
			tranferSessionId = RandomIdUtil.newRandomStringId24();
		}
		ISessionManager sessionManager = serviceServerConfig.getSessionManager();
		ServiceServerSession serviceSession = (ServiceServerSession) sessionManager.getSession(tranferSessionId);
		if (serviceSession == null) {
			serviceSession = new ServiceServerSession(tranferSessionId, config.getSessionGroupManager(), serviceServerConfig);
			GGSession addSessionIfAbsent = sessionManager.addSessionIfAbsent(serviceSession);
			if (addSessionIfAbsent != null) {
				serviceSession = (ServiceServerSession) addSessionIfAbsent;
			}
		}
		
		//提交任务到业务服务端
		Pack pack = new Pack(serviceSession, req.getAction(), req.getMessage());
		serviceServer.submitTask(new RequestMessageTask(pack , serviceServerConfig));
	}

}
