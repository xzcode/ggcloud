package com.xzcode.ggcloud.router.client.router.service.impl;

import com.xzcode.ggcloud.discovery.client.services.DiscoveryClientService;
import com.xzcode.ggcloud.router.client.constant.RouterServiceExtraDataKeys;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceMatcher;

import xzcode.ggserver.core.common.message.Pack;

/**
 * 默认注册中心相关actionid前缀路由服务匹配器
 * 
 * @author zai
 * 2020-02-07 11:34:00
 */
public class DefaultDiscoveryRouterServiceActionPrefixMatcher implements IRouterServiceMatcher {
	
	private String prefix;
	

	public DefaultDiscoveryRouterServiceActionPrefixMatcher(DiscoveryClientService service) {
		prefix = service.getExtraData().get(RouterServiceExtraDataKeys.ROUTER_SERVICE_ACTION_ID_PREFIX);
	}




	@Override
	public boolean match(Pack pack) {
		return pack.getActionString().startsWith(prefix);
	}

}
