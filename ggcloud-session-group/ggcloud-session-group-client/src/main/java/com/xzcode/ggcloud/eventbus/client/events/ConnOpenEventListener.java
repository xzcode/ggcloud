package com.xzcode.ggcloud.eventbus.client.events;

import com.xzcode.ggcloud.eventbus.client.config.SessionGroupClientConfig;
import com.xzcode.ggcloud.session.group.common.message.req.AuthReq;

import xzcode.ggserver.core.common.event.IEventListener;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 连接打开事件监听
 *
 * @author zai
 * 2020-04-08 11:23:13
 */
public class ConnOpenEventListener implements IEventListener<Void>{

	private SessionGroupClientConfig config;
	
	public ConnOpenEventListener(SessionGroupClientConfig config) {
		this.config = config;
	}

	@Override
	public void onEvent(EventData<Void> e) {
		//打开连接，发送认证
		GGSession session = e.getSession();
		session.send(new AuthReq(config.getAuthToken()));
	}

}
