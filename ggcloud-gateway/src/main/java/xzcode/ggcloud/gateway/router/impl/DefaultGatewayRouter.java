package xzcode.ggcloud.gateway.router.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import xzcode.ggcloud.gateway.config.GGGatewayConfig;
import xzcode.ggcloud.gateway.router.IGGCGatewayRouter;
import xzcode.ggcloud.gateway.router.resolver.IGGGatewayRouterResolver;
import xzcode.ggcloud.gateway.router.resolver.ResolvePack;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements IGGCGatewayRouter{
	
	private GGGatewayConfig config;
	
	/**
	 * 路由解析器集合
	 */
	private List<IGGGatewayRouterResolver> routerResolvers = new ArrayList<>();;
	
	/**
	 * 添加路由解析器
	 * @param resolver
	 * 
	 * @author zai
	 * 2019-10-03 14:03:22
	 */
	public void addResolver(IGGGatewayRouterResolver resolver) {
		this.routerResolvers.add(resolver);
	}

	public DefaultGatewayRouter(GGGatewayConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void route(PackModel packModel, GGSession session) {
		String action = new String(packModel.getAction(), Charset.forName(config.getCharset()));
		for (IGGGatewayRouterResolver resolver : routerResolvers) {
			if (resolver.match(action)) {
				resolver.resolve(new ResolvePack(action, packModel, session));
			}
			
		}
	}
	

}
