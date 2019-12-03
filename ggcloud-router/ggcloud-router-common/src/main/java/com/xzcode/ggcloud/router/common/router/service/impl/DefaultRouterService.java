package com.xzcode.ggcloud.router.common.router.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzcode.ggcloud.router.common.config.RouterConfig;
import com.xzcode.ggcloud.router.common.router.service.IRouterService;
import com.xzcode.ggcloud.router.common.router.service.IRouterServiceMatcher;

import io.netty.util.concurrent.DefaultThreadFactory;
import xzcode.ggserver.core.client.GGClient;
import xzcode.ggserver.core.client.config.GGClientConfig;
import xzcode.ggserver.core.common.event.GGEvents;
import xzcode.ggserver.core.common.event.model.EventData;
import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.meta.UserMetadata;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认路由服务
 * 
 * @author zai
 * 2019-11-07 16:52:05
 */
public class DefaultRouterService implements IRouterService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouterService.class);
	
	protected RouterConfig config;
	
	protected String serviceId;
	
	protected String host;
	
	protected IRouterServiceMatcher serviceMatcher;
	
	protected int port;
	
	
	/**
	 * 目标session
	 */
	protected GGSession distSession;
	
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
		clientConfig.setWorkThreadSize(config.getServiceConnectionSize() * 2);
		distClient = new GGClient(clientConfig);
		ISerializer serializer = clientConfig.getSerializer();
		
		/**
		 * 监听连接打开事件
		 */
		distClient.addEventListener(GGEvents.Connection.OPENED, (EventData<Void> data) -> {
			distSession = data.getSession();
		});
		
		/**
		 * 监听连接断开事件
		 */
		distClient.addEventListener(GGEvents.Connection.CLOSED, (EventData<Void> data) -> {
			//10s后
			distClient.schedule(10, TimeUnit.MILLISECONDS, () -> {
				//进行重新连接
				distClient.connect(host, port);
			});
		});
		distClient.addResponseFilter((Response response) -> {
			return false;
		});
		distClient.addBeforeDeserializeFilter((Pack pack) -> {
			//对远端返回的包进行处理
				byte[] metadata = pack.getMetadata();
				try {
					UserMetadata userMetadata = serializer.deserialize(metadata, UserMetadata.class);
					if (userMetadata != null) {
						GGSession routingServerSession = config.getRoutingServer().getConfig().getSessionManager().getSession(userMetadata.getUserId());					
						if (routingServerSession != null) {
							pack.setMetadata(null);
							routingServerSession.send(pack, 0, TimeUnit.MILLISECONDS);
						}
					}
				} catch (Exception e) {
					LOGGER.error("Deserialize metadata Error!", e);
				}
				return false;
		});
		
		distClient.addAfterSerializeFilter((Pack pack) -> {
			//对发送到远端的包进行处理
			try {
				Object metadata = null;
				pack.setMetadata(serializer.serialize(metadata));
			} catch (Exception e) {
				LOGGER.error("Serialize metadata Error!", e);
			}
			
			return true;
		});
		
		//进行连接
		distClient.connect(host, port);
		
	}
	
	/**
	 * 转发消息
	 * 
	 * @param pack
	 * @author zai
	 * 2019-11-07 17:53:00
	 */
	public void dispatch(Pack pack) {
		distSession.send(pack);
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
