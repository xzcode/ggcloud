package xzcode.ggcloud.gateway.router;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;
import xzcode.ggserver.core.common.future.IGGFuture;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 网关路由器统一接口
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:28
 */
public interface IGatewayRouter {
	
	/**
	 * 切换服务
	 * 
	 * @param session
	 * @param service
	 * @return 操作future对象，如果切换服务失败，返回null
	 * @author zai
	 * 2019-10-22 16:44:48
	 */
	IGGFuture switchService(GGSession session, IRouterService service);
	
	/**
	 * 切换服务
	 * 
	 * @param session
	 * @param serviceName
	 * @return 操作future对象，如果切换服务失败，返回null
	 * @author zai
	 * 2019-10-22 16:44:48
	 */
	IGGFuture switchService(GGSession session, String serviceName);
	
}
