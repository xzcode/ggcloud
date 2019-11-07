package xzcode.ggcloud.gateway.router.service.impl;

import xzcode.ggcloud.gateway.router.service.IRouterService;
import xzcode.ggcloud.gateway.router.service.IRouterServiceMatcher;

/**
 * 默认路由服务
 * 
 * @author zai
 * 2019-11-07 16:52:05
 */
public class DefaultRouterService implements IRouterService{
	
	protected String id;
	
	protected IRouterServiceMatcher serviceMatcher;
	
	protected String host;
	
	protected int port;
	
	protected DefaultMessageDispatcher messageDispatcher;
	

	public void setServiceMatcher(IRouterServiceMatcher serviceMatcher) {
		this.serviceMatcher = serviceMatcher;
	}
	

	@Override
	public IRouterServiceMatcher getServiceMatcher() {
		return null;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}


	public void setId(String id) {
		this.id = id;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
