package com.xzcode.ggcloud.eventbus.common.service.listener;

import com.xzcode.ggcloud.eventbus.common.service.ServiceInfo;

/**
 * 注册服务监听器接口
 * 
 * @author zai
 * 2020-02-06 15:05:30
 */
public interface IRegisterServiceListener {
	
	/**
	 * 注册服务时执行
	 * 
	 * @param service
	 * @author zai
	 * 2020-02-06 15:07:24
	 */
	void onRegister(ServiceInfo service);

}
