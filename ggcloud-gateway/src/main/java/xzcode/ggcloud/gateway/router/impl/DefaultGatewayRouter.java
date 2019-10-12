package xzcode.ggcloud.gateway.router.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import xzcode.ggcloud.gateway.config.GGGatewayConfig;
import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggcloud.gateway.router.binding.RouterSessionBinding;
import xzcode.ggcloud.gateway.router.resolve.provider.IResolverProvider;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterResolver;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.server.GGServer;

/**
 * 默认网关路由器
 * 
 * 
 * @author zai
 * 2019-10-03 14:04:16
 */
public class DefaultGatewayRouter implements IGatewayRouter{
	
	private GGServer ggServer;
	
	private GGGatewayConfig config;
	
	private IResolverProvider resolverProvider;
	
	
	protected Map<GGSession, List<RouterSessionBinding>> sessionBindings = new ConcurrentHashMap<>();
	

	public DefaultGatewayRouter(GGGatewayConfig config) {
		this.config = config;
	}
	
	public void init() {
		ggServer.onEvent(GGEvents.ConnectionState.CLOSE, (e) -> {
			//用户客户端断开连接后，移除session绑定
			GGSession session = ggServer.getSession();
			List<RouterSessionBinding> bindingList = sessionBindings.remove(session);
			for (RouterSessionBinding binding : bindingList) {
				binding.disconnect();
			}
		});
	}

	@Override
	public boolean route(PackModel packModel, GGSession session) {
		List<IRouterResolver> routerResolvers = resolverProvider.match(packModel);
		if (routerResolvers == null) {
			return false;
		}
		List<RouterSessionBinding> bindingList = sessionBindings.get(session);
		if (bindingList == null) {
			synchronized (session) {
				bindingList = sessionBindings.get(session);
				if (bindingList == null) {
					bindingList = new CopyOnWriteArrayList<RouterSessionBinding>();
					for (IRouterResolver resolver : routerResolvers) {
						bindingList.add(new RouterSessionBinding(session, resolver));						
					}
					sessionBindings.put(session, bindingList);
				}
			}
		}
		
		//如果路由数量发生了变化
		if (bindingList.size() != routerResolvers.size()) {
			for (IRouterResolver resolver : routerResolvers) {
				bindingList.add(new RouterSessionBinding(session, resolver));						
			}
		}
		return true;
	}
	
	

}
