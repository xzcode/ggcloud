package com.xzcode.ggcloud.router.client;

import com.xzcode.ggserver.core.common.future.IGGFuture;
import com.xzcode.ggserver.core.common.message.Pack;

/**
 * 网关路由器统一接口
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:28
 */
public interface RouterClient {
	
	/**
	 * 路由消息
	 * 
	 * @param session
	 * @param pack
	 * @return 
	 * 
	 * @author zai
	 * 2019-11-06 17:48:12
	 */
	IGGFuture route(Pack pack);
	
	
}
