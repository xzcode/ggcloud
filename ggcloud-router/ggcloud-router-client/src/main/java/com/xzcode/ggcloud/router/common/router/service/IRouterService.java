package com.xzcode.ggcloud.router.common.router.service;

import xzcode.ggserver.core.common.message.Pack;

/**
 * 路由服务统一接口
 * 
 * @author zai
 * 2019-11-07 16:51:06
 */
public interface IRouterService {
	
	/**
	 * 获取服务id
	 * 
	 * @return
	 * @author zai
	 * 2019-11-07 16:50:36
	 */
	String getServiceId();
	
	/**
	 * 获取服务匹配器
	 * 
	 * @return
	 * @author zai
	 * 2019-11-07 16:50:29
	 */
	IRouterServiceMatcher getServiceMatcher();
	
	/**
	 * 获取服务地址
	 * 
	 * @return
	 * @author zai
	 * 2019-11-07 16:50:42
	 */
	String getHost();
	
	/**
	 * 获取服务端口
	 * 
	 * @return
	 * @author zai
	 * 2019-11-07 16:50:57
	 */
	int getPort();
	
	
	/**
	 * 进行消息转发
	 * @param pack
	 * 
	 * @author zai
	 * 2019-11-11 21:40:46
	 */
	void dispatch(Pack pack);
	
	
	
}
