package com.xzcode.ggcloud.eventbus.client.subscription;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggserver.core.common.session.GGSession;

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
	private Map<String, GGSession> sessions = new ConcurrentHashMap<>();
	
	
	public Subscription(String eventId) {
		super();
		this.eventId = eventId;
	}
	
	public void publish() {
		
	}

	public void addSubscription(GGSession session) {
		sessions.put(session.getSessonId(), session);
	}
	
	public void removeSubscription(GGSession session) {
		sessions.remove(session.getSessonId());
	}
	
	
	
}
