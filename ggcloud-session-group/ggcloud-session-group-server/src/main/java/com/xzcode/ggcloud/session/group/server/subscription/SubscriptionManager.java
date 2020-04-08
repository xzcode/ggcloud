package com.xzcode.ggcloud.session.group.server.subscription;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggserver.core.common.session.group.GGSessionGroup;

/**
 * 事件订阅处理器
 *
 * @author zai
 * 2020-04-07 11:18:51
 */
public class SubscriptionManager {
	
	
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
	public void addSubscription(String eventId, GGSessionGroup sessionGroup) {
		Subscription subscription = subscriptions.get(eventId);
		if (subscription == null) {
			subscription = new Subscription(eventId);
			Subscription putIfAbsent = subscriptions.putIfAbsent(eventId, subscription);
			if (putIfAbsent != null) {
				subscription = putIfAbsent;
			}
		}
		subscription.addSubscription(sessionGroup);
	}
	
	/**
	 * 移除订阅
	 *
	 * @param eventId
	 * @param sessionGroup
	 * @author zai
	 * 2020-04-07 15:40:47
	 */
	public void removeSubscription(String eventId, GGSessionGroup sessionGroup) {
		Subscription subscription = subscriptions.get(eventId);
		if (subscription != null) {
			subscription.removeSubscription(sessionGroup);
		}
	}
	
}
