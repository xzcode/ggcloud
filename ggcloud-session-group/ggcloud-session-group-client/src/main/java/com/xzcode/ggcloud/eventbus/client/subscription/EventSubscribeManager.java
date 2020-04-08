package com.xzcode.ggcloud.eventbus.client.subscription;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggserver.core.common.session.GGSession;

/**
 * 事件订阅处理器
 *
 * @author zai
 * 2020-04-07 11:18:51
 */
public class EventSubscribeManager {
	
	
	//事件订阅集合
	private Map<String, Subscription> subscriptions = new ConcurrentHashMap<String, Subscription>();
	
	/**
	 * 添加订阅
	 *
	 * @param eventId
	 * @param session
	 * @author zai
	 * 2020-04-07 11:45:20
	 */
	public void addSubscription(String eventId, GGSession session) {
		Subscription subscription = subscriptions.get(eventId);
		if (subscription == null) {
			subscription = new Subscription(eventId);
			Subscription putIfAbsent = subscriptions.putIfAbsent(eventId, subscription);
			if (putIfAbsent != null) {
				subscription = putIfAbsent;
			}
		}
		subscription.addSubscription(session);
	}
	
	public void removeSubscription(String eventId, GGSession session) {
		Subscription subscription = subscriptions.get(eventId);
		if (subscription != null) {
			subscription.removeSubscription(session);
		}
	}
	
	public void removeSubscription(GGSession session) {
		
	}
	
}
