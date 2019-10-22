package xzcode.ggcloud.gateway.router.resolve.provider.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggcloud.gateway.router.resolve.provider.IRouterServiceProvider;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterService;

/**
 * 默认解析器
 * 
 * @author zai
 * 2019-10-12 14:54:31
 */
public class DefaultServicePorvider implements IRouterServiceProvider{
	
	protected Map<String, IRouterService> services = new ConcurrentHashMap<>();

	@Override
	public IRouterService getService(String serviceName) {
		return services.get(serviceName);
	}

	@Override
	public IRouterService addRouterService(String serviceName, IRouterService service) {
		return services.put(serviceName, service);
	}

	@Override
	public IRouterService removeRouterService(String serviceName) {
		return services.remove(serviceName);
		
	}
	
	
	
	
}
