package xzcode.ggcloud.gateway.router;

import xzcode.ggserver.core.common.message.Pack;
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
	 * 路由消息
	 * 
	 * @param session
	 * @param pack
	 * @return
	 * @author zai
	 * 2019-11-06 17:48:12
	 */
	void route(GGSession session, Pack pack);
	
	
}
