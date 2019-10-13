package xzcode.ggcloud.gateway.router.resolve.resolver;

import xzcode.ggserver.core.common.message.PackModel;

/**
 * 路由解析器
 * 
 * @author zai
 * 2019-10-12 14:57:08
 */
public interface IRouterResolver {
	
	boolean match(PackModel packModel);
	
	String getHost();
	
	int getPort();
	
	
	
}
