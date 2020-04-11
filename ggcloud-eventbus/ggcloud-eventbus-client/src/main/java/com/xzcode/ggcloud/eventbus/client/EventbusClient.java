package com.xzcode.ggcloud.eventbus.client;

import java.nio.charset.Charset;

import com.xzcode.ggcloud.eventbus.client.config.EventbusClientConfig;
import com.xzcode.ggcloud.eventbus.client.subscriber.Subscriber;
import com.xzcode.ggcloud.eventbus.common.constant.EventbusConstant;
import com.xzcode.ggcloud.eventbus.common.message.req.EventPublishReq;
import com.xzcode.ggcloud.session.group.client.SessionGroupClient;
import com.xzcode.ggcloud.session.group.client.config.SessionGroupClientConfig;
import com.xzcode.ggcloud.session.group.common.message.req.DataTransferReq;

import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.executor.thread.GGThreadFactory;
import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.MessageData;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.response.support.IMakePackSupport;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

public class EventbusClient implements IMakePackSupport{
	
	private EventbusClientConfig config;
	
	private ISerializer serializer;
	
	private Charset charset;
	
	
	public EventbusClient(EventbusClientConfig config) {
		this.config = config;
		this.config.setEventbusClient(this);
	}

	public void start() {
		
		SessionGroupClientConfig sessionGroupClientConfig = new SessionGroupClientConfig();
		sessionGroupClientConfig.setEnableServiceClient(true);
		sessionGroupClientConfig.setAuthToken(this.config.getAuthToken());
		sessionGroupClientConfig.setWorkThreadFactory(new GGThreadFactory("gg-evt-cli-", false));
		sessionGroupClientConfig.setConnectionSize(this.config.getConnectionSize());
		
		SessionGroupClient sessionGroupClient = new SessionGroupClient(sessionGroupClientConfig);
		
		//包日志输出控制
		if (this.config.isPrintEventbusPackLog()) {
			sessionGroupClientConfig.getSessionClient().getConfig().getPackLogger().addPackLogFilter(pack -> {
				String actionString = pack.getActionString();
				return !(actionString.startsWith(EventbusConstant.ACTION_ID_PREFIX));
			});
		}
		
		this.serializer = sessionGroupClient.getConfig().getSessionClient().getSerializer();
		this.charset = sessionGroupClient.getConfig().getSessionClient().getCharset();
		
		sessionGroupClient.start();
		
	}
	
	/**
	 * 发布事件消息
	 *
	 * @param eventId
	 * @author zai
	 * 2020-04-11 18:12:48
	 */
	public void publishEvent(String eventId, Object data) {
		try {
			
			DataTransferReq transferReq = new DataTransferReq();
			
			EventPublishReq publishReq = new EventPublishReq();
			publishReq.setEventData(getSerializer().serialize(data));
			publishReq.setEventId(eventId);
			
			Pack publishPack = makePack(new MessageData<>(EventPublishReq.ACTION_ID, publishReq));
			
			transferReq.setAction(publishPack.getAction());
			transferReq.setMessage(publishPack.getMessage());
			
			this.config.getSessionGroupClient().transferData(transferReq);
			
		} catch (Exception e) {
			GGLoggerUtil.getLogger(this).error("Eventbus publish event ERROR!", e);
		}
	}
	
	public <T> void subscribe(String eventId, Subscriber<T> subscriber) {
		this.config.getSubscribeManager().addSubscriber(eventId, subscriber);
	}

	@Override
	public Charset getCharset() {
		return this.charset;
	}

	@Override
	public ISerializer getSerializer() {
		return this.serializer;
	}
	

}
