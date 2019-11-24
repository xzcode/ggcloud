package xzcode.ggcloud.gateway.router.service.listener;

import xzcode.ggcloud.gateway.router.service.IRouterService;

/**
 * 路由服务监听器
 * 
 * @author zai
 * 2019-11-07 17:19:12
 */
public interface IRouterServiceListener {
	
	/**
	 * 触发处理函数
	 * 
	 * @param routerService
	 * @author zai
	 * 2019-11-07 17:19:22
	 */
	void trigger(IRouterService routerService);
	
}
