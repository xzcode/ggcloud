package com.xzcode.ggcloud.router.common.message.register.resp;

/**
 * 路由通道注册请求
 * 
 * @author zai
 * 2019-12-19 15:36:31
 */
public class RouterChannelRegisterResp {
	
	public static final String ACTION_ID = "ROUTER.CHANNEL.REGISTER.RESP";
	
	/**
	 * 是否绑定成功
	 */
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
