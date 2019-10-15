package xzcode.ggcloud.gateway.router.resolve.provider.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import xzcode.ggcloud.gateway.router.resolve.provider.IResolverProvider;
import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterResolver;
import xzcode.ggserver.core.common.message.PackModel;

/**
 * 默认解析器
 * 
 * @author zai
 * 2019-10-12 14:54:31
 */
public class DefaultResolverPorvider implements IResolverProvider{
	
	protected Map<String, IRouterResolver> resolvers = new ConcurrentHashMap<>();

	@Override
	public List<IRouterResolver> match(PackModel packModel) {
		List<IRouterResolver> matchedResolvers = null;
		for (Entry<String, IRouterResolver> entry : resolvers.entrySet()) {
			IRouterResolver resolver = entry.getValue();
			if (resolver.match(packModel)) {
				if (matchedResolvers == null) {
					matchedResolvers = new ArrayList<>(1);
				}
				matchedResolvers.add(resolver);
			}
		}
		return matchedResolvers;
	}

	public IRouterResolver addResolver(String resolverId, IRouterResolver resolver) {
		return resolvers.put(resolverId, resolver);
	}

	public IRouterResolver removeResolver(String resolverId) {
		return resolvers.remove(resolverId);
		
	}
	
	
	
	
}
