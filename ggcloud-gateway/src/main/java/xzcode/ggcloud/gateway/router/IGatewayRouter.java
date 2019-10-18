package xzcode.ggcloud.gateway.router;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 网关路由器统一接口
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:28
 */
public interface IGatewayRouter {
	
	
	void switchService(IRouterService service, GGSession session);
	
}
