package com.xzcode.ggcloud.router.client.router.meta;

import xzcode.ggserver.core.common.message.meta.IMetadata;

/**
 * 用户id元数据
 * 
 * @author zai
 * 2019-12-12 17:51:34
 */
public class RouterUserIdMetadata implements IMetadata {
	
	public static final String METADATA_SESSION_KEY = "ROUTER_METADATA";
	/**
	 * 路由客户端id
	 */
	private String routerId;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	
	public RouterUserIdMetadata() {
	}

	public RouterUserIdMetadata(String routerId, String userId) {
		super();
		this.routerId = routerId;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getRouterId() {
		return routerId;
	}
	
	public void setRouterId(String routerId) {
		this.routerId = routerId;
	}

}
