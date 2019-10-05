package com.xzcode.ggcloud.discovery.client.services;

/**
 * 服务信息
 * 
 * 
 * @author zai
 * 2019-10-04 16:14:45
 */
public class ServiceInfo {
	
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
	 * 负载参考值
	 */
	private long loadingCapacity;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
