package com.xzcode.ggcloud.router.client.router.service;

import com.xzcode.ggserver.core.common.message.Pack;

/**
 * 路由包处理接口
 * 
 * @author zai
 * 2019-12-12 18:34:46
 */
public interface IRouterPackHandler {
	
	/**
	 * 处理回包
	 * 
	 * @param pack
	 * @author zai
	 * 2019-12-12 18:39:25
	 */
	void handleReceivePack(Pack pack);
	
	/**
	 * 处理发包
	 * 
	 * @param pack
	 * @author zai
	 * 2019-12-12 18:53:55
	 */
	void handleSendPack(Pack pack);

}
