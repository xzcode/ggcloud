package com.xzcode.ggcloud.discovery.server.services;

import java.util.LinkedHashMap;
import java.util.Map;

import xzcode.ggserver.core.common.session.GGSession;

/**
 * 服务信息
 * 
 * 
 * @author zai
 * 2019-10-04 16:14:45
 */
public class ServiceInfo {
	
	/**
	 * 会话
	 */
	private GGSession session;
	
	/**
	 * 服务id
	 */
	private String serviceId;
	
	/**
	 * 服务名称
	 */
	private String serviceName;
	
	/**
	 * 服务ip地址
	 */
	private String ip;
	
	/**
	 * 所在地区
	 */
	private String region;
	
	/**
	 * 所在分区
	 */
	private String zone;
	
	/**
	 * 服务ip地址
	 */
	private int port;
	
	/**
	 * 服务过期延迟（毫秒）
	 */
	private long timeoutDelay;
	
	/**
	 * 超时时间戳
	 */
	private long timeoutTimestamp;
	
	/**
	 * 负载参考值
	 */
	private long loadingCapacity;
	
	/**
	 * 额外数据
	 */
	private Map<String, String> extraData;
	
	/**
	 * 添加额外参数
	 * 
	 * @param key
	 * @param value
	 * @author zai
	 * 2020-02-04 11:19:05
	 */
	public void addExtraData(String key, String value) {
		if (extraData == null) {
			extraData = new LinkedHashMap<>();
		}
		extraData.put(key, value);
	}
	
	/**
	 * 刷新超时时间
	 * @param delay
	 * 
	 * @author zai
	 * 2019-10-04 16:39:05
	 */
	public void refreshExpireTime() {
		this.timeoutTimestamp = System.currentTimeMillis() + timeoutDelay;
	}
	
	/**
	 * 是否超时
	 * 
	 * @author zai
	 * 2020-02-03 16:23:42
	 */
	public boolean isTimeout() {
		return timeoutTimestamp < System.currentTimeMillis();
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public long getTimeoutDelay() {
		return timeoutDelay;
	}

	public void setTimeoutDelay(long expireTimestamp) {
		this.timeoutDelay = expireTimestamp;
	}

	public long getLoadingCapacity() {
		return loadingCapacity;
	}

	public void setLoadingCapacity(long loadingCapacity) {
		this.loadingCapacity = loadingCapacity;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public GGSession getSession() {
		return session;
	}
	
	public void setSession(GGSession session) {
		this.session = session;
	}
	
	public long getTimeoutTimestamp() {
		return timeoutTimestamp;
	}
	
	public Map<String, String> getExtraData() {
		return extraData;
	}
	
	public void setExtraData(Map<String, String> extraData) {
		this.extraData = extraData;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
}
