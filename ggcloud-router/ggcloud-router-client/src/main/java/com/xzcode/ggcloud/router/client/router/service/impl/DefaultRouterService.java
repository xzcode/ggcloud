package com.xzcode.ggcloud.router.client.router.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterService;
import com.xzcode.ggcloud.router.client.router.service.IRouterServiceMatcher;

import io.netty.util.concurrent.DefaultThreadFactory;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认路由服务
 * 
 * @author zai
 * 2019-11-07 16:52:05
 */
public class DefaultRouterService implements IRouterService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouterService.class);
	
	protected RouterClientConfig config;
	
	protected String serviceId;
	
	protected String host;
	
	protected int port;
	
	protected IRouterServiceMatcher serviceMatcher;
	
	/**
	 * 绑定的连接客户端
	 */
	protected GGClient distClient;
	
	/**
	 * 是否已关闭
	 */
	protected boolean down;


	/**
	 * 初始化
	 * 
	 * @author zai
	 * 2019-11-07 15:50:25
	 */
	public void init() {
		
		GGClientConfig clientConfig = new GGClientConfig();
		clientConfig.setWorkerGroupThreadFactory(new DefaultThreadFactory("router-service-" + this.serviceId + "-", false));
		clientConfig.setMetadataResolver(config.getMetadataResolver());
		clientConfig.setMetadataProvider(config.getMetadataProvider());
		clientConfig.setHost(host);
		clientConfig.setPort(port);
		clientConfig.setChannelPoolEnabled(true);
		distClient = new GGClient(clientConfig);
		

		distClient.addAfterSerializeFilter((Pack pack) -> {
			//对发送到远端的包进行处理
			config.getPackHandler().handleSendPack(pack);
			return true;
		});
		
		distClient.addBeforeDeserializeFilter((Pack pack) -> {
			//对远端返回的包进行处理
			config.getPackHandler().handleReceivePack(pack);
			return false;
		});
		
		
	}
	
	/**
	 * 转发消息
	 * 
	 * @param pack
	 * @author zai
	 * 2019-11-07 17:53:00
	 */
	public void dispatch(Pack pack) {
		distClient.send(pack);
	}
	
	/**
	 * 关闭
	 * 
	 * @author zai
	 * 2019-11-07 15:51:04
	 */
	public void shutdown() {
		this.down = true;
		this.distClient.shutdown();
	}


	public void setServiceMatcher(IRouterServiceMatcher serviceMatcher) {
		this.serviceMatcher = serviceMatcher;
	}
	

	@Override
	public IRouterServiceMatcher getServiceMatcher() {
		return this.serviceMatcher;
	}
	
	@Override
	public String getServiceId() {
		return this.serviceId;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
