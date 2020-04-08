package com.xzcode.ggcloud.session.group.server.handler;

import com.xzcode.ggcloud.session.group.common.message.req.AuthReq;
import com.xzcode.ggcloud.session.group.common.message.req.DataTransferReq;
import com.xzcode.ggcloud.session.group.common.message.resp.AuthResp;
import com.xzcode.ggcloud.session.group.server.config.SessionGroupServerConfig;

import xzcode.ggserver.core.common.handler.codec.IDecodeHandler;
import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.message.request.task.RequestMessageTask;
import xzcode.ggserver.core.common.session.GGSession;
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
		GGSession session = request.getSession();
		DataTransferReq req = request.getMessage();
		
		IGGServer serviceServer = config.getServiceServer();
		GGServerConfig serviceServerConfig = serviceServer.getConfig();
		ISerializer serializer = serviceServerConfig.getSerializer();
		
		String actionId = new String(req.getAction(), serviceServerConfig.getCharset());
		
		Pack pack = new Pack(metadata, req.getAction(), req.getMessage());
		
		RequestMessageTask requestMessageTask = new RequestMessageTask(pack , config);
		
		IDecodeHandler decodeHandler = serviceServerConfig.getDecodeHandler();
		decodeHandler.handle(ctx, in, protocolType);
	}

}
