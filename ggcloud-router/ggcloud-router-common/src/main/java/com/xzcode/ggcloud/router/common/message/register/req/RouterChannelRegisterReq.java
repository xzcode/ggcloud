package com.xzcode.ggcloud.router.common.message.register.req;

/**
 * 路由通道注册请求
 * 
 * @author zai
 * 2019-12-19 15:36:31
 */
public class RouterChannelRegisterReq {
	public static final String ACTION_ID = "ROUTER.CHANNEL.REGISTER.REQ";
	
	/**
	 * 路由组id
	 */
	private String routerGroupId;
	

	public RouterChannelRegisterReq() {
	}

	public RouterChannelRegisterReq(String routerGroupId) {
		this.routerGroupId = routerGroupId;
	}

	public String getRouterGroupId() {
		return routerGroupId;
	}

	public void setRouterGroupId(String routerGroupId) {
		this.routerGroupId = routerGroupId;
	}
}
