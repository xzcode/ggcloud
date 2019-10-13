package xzcode.ggcloud.gateway.router.resolve.provider.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	
	protected List<IRouterResolver> resolvers = new CopyOnWriteArrayList<>();

	@Override
	public List<IRouterResolver> match(PackModel packModel) {
		List<IRouterResolver> matchedResolvers = null;
		for (IRouterResolver resolver : resolvers) {
			if (resolver.match(packModel)) {
				if (matchedResolvers == null) {
					matchedResolvers = new ArrayList<>(1);
				}
				matchedResolvers.add(resolver);
			}
		}
		return matchedResolvers;
	}

	@Override
	public void addRouterResolver(IRouterResolver resolver) {
		resolvers.add(resolver);
	}
	
	
	
	
}
