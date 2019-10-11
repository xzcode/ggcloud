package com.xzcode.ggcloud.discovery.server.services;

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
	 * 客户端服务会话
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
	 * 服务ip地址
	 */
	private int port;
	
	/**
	 * 服务过期时间,时间戳
	 */
	private long expireTimestamp;
	
	/**
	 * 负载参考值
	 */
	private long loadingCapacity;
	
	/**
	 * 刷新超时时间
	 * @param delay
	 * 
	 * @author zai
	 * 2019-10-04 16:39:05
	 */
	public void refreshExpireTime(long delay) {
		this.expireTimestamp = System.currentTimeMillis() + delay;
	}
	
	public GGSession getSession() {
		return session;
	}
	public void setSession(GGSession session) {
		this.session = session;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public long getExpireTimestamp() {
		return expireTimestamp;
	}

	public void setExpireTimestamp(long expireTimestamp) {
		this.expireTimestamp = expireTimestamp;
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
	
	
	
}
