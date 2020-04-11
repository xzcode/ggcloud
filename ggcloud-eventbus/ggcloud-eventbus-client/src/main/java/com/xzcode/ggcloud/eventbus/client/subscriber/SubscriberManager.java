package com.xzcode.ggcloud.eventbus.client.subscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 订阅器管理器
 *
 * @author zai
 * 2020-04-11 18:10:43
 */
public class SubscriberManager {
	
	
	//事件订阅器组集合
	private Map<String, SubscriberGroup> groups = new ConcurrentHashMap<String, SubscriberGroup>();
	
	/**
	 * 添加订阅器
	 *
	 * @param eventId
	 * @param subscriber
	 * @author zai
	 * 2020-04-11 18:10:20
	 */
	public <T> void addSubscriber(String eventId, SubscriberInfo subscriber) {
		SubscriberGroup group = groups.get(eventId);
		if (group == null) {
			group = new SubscriberGroup(eventId);
			SubscriberGroup putIfAbsent = groups.putIfAbsent(eventId, group);
			if (putIfAbsent != null) {
				group = putIfAbsent;
			}
		}
		group.add(subscriber);
	}
	
	/**
	 * 移除订阅器
	 *
	 * @param eventId
	 * @param subscriber
	 * @author zai
	 * 2020-04-11 18:10:05
	 */
	public void removeSubscriber(String eventId, SubscriberInfo subscriber) {
		SubscriberGroup group = groups.get(eventId);
		if (group != null) {
			group.remove(subscriber);
		}
	}
	
	/**
	 * 触发订阅器
	 *
	 * @param eventId
	 * @param eventbusMessage
	 * @author zai
	 * 2020-04-11 18:09:54
	 */
	public void trigger(String eventId, String subscriberId, Object data) {
		SubscriberGroup group = groups.get(eventId);
		if (group != null) {
			group.trigger(subscriberId, data);
		}
	}
	
}
