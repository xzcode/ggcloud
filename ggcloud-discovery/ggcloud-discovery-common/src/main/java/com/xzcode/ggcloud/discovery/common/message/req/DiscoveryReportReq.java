package com.xzcode.ggcloud.discovery.common.message.req;

public class DiscoveryReportReq {
	
	public static final String ACTION = "DISCOVERY.REPORT.REQ";
	
	/**
	 * 负载参考值
	 */
	private int loadingCapacity;
	
	
	public int getLoadingCapacity() {
		return loadingCapacity;
	}
	
	public void setLoadingCapacity(int loadingCapacity) {
		this.loadingCapacity = loadingCapacity;
	}
	
}
