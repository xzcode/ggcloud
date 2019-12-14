package com.xzcode.ggcloud.router.client.router.service.impl;

import java.util.Map;

import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceMatcher;

import xzcode.ggserver.core.common.message.Pack;

/**
 * 默认action前缀路由服务匹配器
 * 
 * @author zai
 * 2019-11-07 17:29:02
 */
public class ActionPrefixRouterServiceMatcher implements IRouterServiceMatcher {
	

	@Override
	public IRouterService match(Pack pack, Map<String, IRouterService> services) {
		String action = pack.getActionString();
		for (String key : services.keySet()) {
			if (action.startsWith(key)) {
				return services.get(key);
			}
		}
		return null;
	}

}