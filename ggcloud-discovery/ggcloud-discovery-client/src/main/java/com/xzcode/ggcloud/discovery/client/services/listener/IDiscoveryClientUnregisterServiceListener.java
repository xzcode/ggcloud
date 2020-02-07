package com.xzcode.ggcloud.discovery.client.services.listener;

import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientService;

/**
 * 取消注册服务监听器接口
 * 
 * @author zai
 * 2020-02-06 15:05:30
 */
public interface IDiscoveryClientUnregisterServiceListener {

	/**
	 * 取消注册服务时执行
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-06 15:07:24
	 */
	void onUnregister(DiscoveryClientService service);
	
}
