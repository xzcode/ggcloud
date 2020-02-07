package com.xzcode.ggcloud.discovery.client.services.listener;

import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientService;

/**
 * 更新服务监听器接口
 * 
 * @author zai
 * 2020-02-06 15:05:30
 */
public interface IDiscoveryClientUpdateServiceListener {
	
	/**
	 * 更新注册服务时执行
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-06 15:07:24
	 */
	void onRegister(DiscoveryClientService service);

}
