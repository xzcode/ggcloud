package xzcode.ggcloud.gateway.router;

import xzcode.ggserver.core.message.PackModel;
import xzcode.ggserver.core.session.GGSession;

/**
 * 网关路由器统一接口
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:28
 */
public interface IGGCGatewayRouter {
	
	
	void route(PackModel packModel, GGSession session);
	
}
