package com.xzcode.ggcloud.eventbus.common.service.listener;

import com.xzcode.ggcloud.eventbus.common.service.ServiceInfo;

/**
 * 取消注册服务监听器接口
 * 
 * @author zai
 * 2020-02-06 15:05:30
 */
public interface IUnregisterServiceListener {

	/**
	 * 取消注册服务时执行
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-06 15:07:24
	 */
	void onUnregister(ServiceInfo service);
	
}
