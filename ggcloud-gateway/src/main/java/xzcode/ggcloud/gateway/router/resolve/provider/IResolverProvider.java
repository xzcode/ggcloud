package xzcode.ggcloud.gateway.router.resolve.provider;

import java.util.List;

import xzcode.ggcloud.gateway.router.resolve.resolver.IRouterResolver;
import xzcode.ggserver.core.common.message.PackModel;

/**
 * 路由解析器提供者
 * 
 * @author zai
 * 2019-10-12 14:57:37
 */
public interface IResolverProvider {
	
	/**
	 * 添加路由解析器
	 * 
	 * @param resolver
	 * @author zai
	 * 2019-10-12 18:19:29
	 */
	void addRouterResolver(IRouterResolver resolver);
	
	/**
	 * 匹配解析器
	 * 
	 * @return
	 * @author zai
	 * 2019-10-12 15:01:39
	 */
	List<IRouterResolver> match(PackModel packModel);
}
