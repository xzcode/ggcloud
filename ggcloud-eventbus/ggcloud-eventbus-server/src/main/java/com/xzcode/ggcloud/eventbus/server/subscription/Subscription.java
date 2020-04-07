package com.xzcode.ggcloud.eventbus.server.subscription;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xzcode.ggcloud.eventbus.common.message.resp.EventMessageResp;

import xzcode.ggserver.core.common.session.group.GGSessionGroup;

/***
 * 订阅信息
 *
 * @author zai
 * 2020-04-07 11:26:42
 */
public class Subscription {
	
	//事件id
	private String eventId;
	
	//订阅的会话集合
	private Map<String, GGSessionGroup> sessions = new ConcurrentHashMap<>();
	
	
	public Subscription(String eventId) {
		super();
		this.eventId = eventId;
	}
	
	public void publish(EventMessageResp resp) {
		for (Entry<String, GGSessionGroup> entry : sessions.entrySet()) {
			GGSessionGroup group = entry.getValue();
			group.sendToRandomOne(resp);
		}
		
	}

	public void addSubscription(GGSessionGroup sessionGroup) {
		sessions.put(sessionGroup.getGroupId(), sessionGroup);
	}
	
	public void removeSubscription(GGSessionGroup sessionGroup) {
		sessions.remove(sessionGroup.getGroupId());
	}
	
	
	
}
