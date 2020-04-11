package com.xzcode.ggcloud.eventbus.server.subscription;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xzcode.ggcloud.eventbus.common.message.resp.EventMessageResp;

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
	
	public void publish(EventMessageResp resp) {
		for (Entry<String, GGSession> entry : sessions.entrySet()) {
			GGSession session = entry.getValue();
			session.send(resp);
		}
	}

	public void addSubscription(GGSession session) {
		sessions.put(session.getSessonId(), session);
		session.addDisconnectListener(se -> {
			removeSubscription(se);
		});
	}
	
	public void removeSubscription(GGSession session) {
		sessions.remove(session.getSessonId());
	}
	
	public String getEventId() {
		return eventId;
	}
	
}
