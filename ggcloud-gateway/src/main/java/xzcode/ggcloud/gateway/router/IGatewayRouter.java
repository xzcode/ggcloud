package xzcode.ggcloud.gateway.router;

import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 网关路由器统一接口
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:28
 */
public interface IGatewayRouter {
	
	
	boolean route(PackModel packModel, GGSession session);
	
}
