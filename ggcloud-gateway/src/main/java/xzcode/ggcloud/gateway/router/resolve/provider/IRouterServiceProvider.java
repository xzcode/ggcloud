package xzcode.ggcloud.gateway.router.resolve.provider;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;

/**
 * 路由解析器提供者
 * 
 * @author zai
 * 2019-10-12 14:57:37
 */
public interface IRouterServiceProvider {
	
	
	
	/**
	 * 匹配解析器
	 * 
	 * @return
	 * @author zai
	 * 2019-10-12 15:01:39
	 */
	IRouterService getService(String serviceName);

	/**
	 * 添加路由服务
	 * 
	 * @param serviceName
	 * @param service
	 * @return
	 * @author zai
	 * 2019-10-22 18:21:53
	 */
	IRouterService addRouterService(String serviceName, IRouterService service);

	/**
	 * 移除路由服务
	 * 
	 * @param serviceName
	 * @return
	 * @author zai
	 * 2019-10-22 18:22:01
	 */
	IRouterService removeRouterService(String serviceName);
}
