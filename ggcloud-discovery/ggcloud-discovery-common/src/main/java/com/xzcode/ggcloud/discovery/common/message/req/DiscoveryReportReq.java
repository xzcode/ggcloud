package com.xzcode.ggcloud.discovery.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

public class DiscoveryReportReq implements IMessage{
	
	public static final String ACTION = "DISCOVERY.REPORT.REQ";
	
	@Override
	public String getActionId() {
		return ACTION;
	}
	
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
