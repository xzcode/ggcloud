package xzcode.ggcloud.gateway.router.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggcloud.gateway.config.GatewayRouterConfig;
import xzcode.ggcloud.gateway.router.service.IRouterService;
import xzcode.ggcloud.gateway.router.service.IRouterServiceMatcher;
import xzcode.ggcloud.gateway.router.service.IRouterServiceProvider;
import xzcode.ggcloud.gateway.router.service.listener.IAddRouterServiceListener;
import xzcode.ggcloud.gateway.router.service.listener.IRemoveRouterServiceListener;
import xzcode.ggcloud.gateway.router.service.listener.IRouterServiceListener;
import xzcode.ggserver.core.common.message.Pack;

/**
 * 默认路由服务提供者
 * 
 * @author zai
 * 2019-11-07 17:36:10
 */
public class DefaultServicePorvider implements IRouterServiceProvider{
	
	protected GatewayRouterConfig config;
	
	protected IRouterServiceMatcher routerServiceMatcher = new ActionPrefixRouterServiceMatcher();
	
	protected List<IAddRouterServiceListener> addRouterServiceListeners = new ArrayList<>();
	
	protected List<IRemoveRouterServiceListener> removeRouterServiceListeners = new ArrayList<>();
	
	protected Map<String, IRouterService> services = new ConcurrentHashMap<>();
	
	public DefaultServicePorvider(GatewayRouterConfig config) {
		this.config = config;
	}

	@Override
	public IRouterService getService(String serviceId) {
		return services.get(serviceId);
	}

	@Override
	public IRouterService addService(IRouterService service) {
		return services.put(service.getServiceId(), service);
	}

	@Override
	public IRouterService removeService(String serviceId) {
		return services.remove(serviceId);
	}

	@Override
	public IRouterService matchService(Pack pack) {
		String action = pack.getActionString(config.getCharset());
		for (String key : services.keySet()) {
			if (action.startsWith(key)) {
				return this.getRouterServiceMatcher().match(pack, services);
			}
		}
		return null;
	}


	@Override
	public void addListener(IRouterServiceListener listener) {
		if (listener instanceof IAddRouterServiceListener) {
			addRouterServiceListeners.add((IAddRouterServiceListener) listener);
			return;
		}
		if (listener instanceof IRemoveRouterServiceListener) {
			removeRouterServiceListeners.add((IRemoveRouterServiceListener) listener);
			return;
		}
	}

	@Override
	public void removeListener(IRouterServiceListener listener) {
		if (listener instanceof IAddRouterServiceListener) {
			addRouterServiceListeners.remove((IAddRouterServiceListener) listener);
			return;
		}
		if (listener instanceof IRemoveRouterServiceListener) {
			removeRouterServiceListeners.remove((IRemoveRouterServiceListener) listener);
			return;
		}
	}

	@Override
	public void setRouterServiceMatcher(IRouterServiceMatcher matcher) {
		this.routerServiceMatcher = matcher;
	}

	@Override
	public IRouterServiceMatcher getRouterServiceMatcher() {
		return this.routerServiceMatcher;
	}
	
	
	
	
}
