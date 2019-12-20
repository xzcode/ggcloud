package com.xzcode.ggcloud.router.common.meta;

import xzcode.ggserver.core.common.message.meta.IMetadata;

/**
 * 用户id元数据
 * 
 * @author zai
 * 2019-12-12 17:51:34
 */
public class RouterSessionIdMetadata implements IMetadata {
	
	public static final String METADATA_SESSION_KEY = "ROUTER_METADATA";
	
	private String sessionId;
	
	
	public RouterSessionIdMetadata() {
		
	}

	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
