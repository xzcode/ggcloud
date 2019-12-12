package com.xzcode.ggcloud.router.client.router.meta;

import xzcode.ggserver.core.common.message.meta.IMetadata;

/**
 * 用户id元数据
 * 
 * @author zai
 * 2019-12-12 17:51:34
 */
public class RouterUserIdMetadata implements IMetadata {
	
	public static final String METADATA_SESSION_KEY = "USER_ID_METADATA";
	
	private String userId;
	
	
	public RouterUserIdMetadata() {
	}

	public RouterUserIdMetadata(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
