package com.xzcode.ggcloud.router.client.router.service.impl;

import java.util.concurrent.TimeUnit;
import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.service.IRouterPackHandler;
import com.xzcode.ggcloud.router.common.meta.RouterSessionIdMetadata;

import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.message.meta.provider.IMetadataProvider;
import xzcode.ggserver.core.common.message.meta.resolver.IMetadataResolver;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.manager.ISessionManager;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 用户id元数据回包处理实现
 * 
 * @author zai 2019-12-12 18:36:44
 */
public class RouterUsereIdMetadataPackHandler implements IRouterPackHandler {

	private IMetadataResolver<RouterSessionIdMetadata> metadataResolver;
	private IMetadataProvider<RouterSessionIdMetadata> metadataProvider;
	private ISerializer serializer;
	private ISessionManager sessionManager;

	

	@SuppressWarnings("unchecked")
	public RouterUsereIdMetadataPackHandler(RouterClientConfig config) {
		this.metadataResolver = (IMetadataResolver<RouterSessionIdMetadata>) config.getMetadataResolver();
		this.metadataProvider = (IMetadataProvider<RouterSessionIdMetadata>) config.getMetadataProvider();
		this.serializer = ((RouterClientConfig) config).getRoutingServer().getConfig().getSerializer();
		this.sessionManager = ((RouterClientConfig) config).getRoutingServer().getConfig().getSessionManager();
	}

	public void handleReceivePack(Pack pack) {
		byte[] metadata = pack.getMetadata();
		try {
			RouterSessionIdMetadata sessionIdMetadata = metadataResolver.resolve(metadata);
			if (sessionIdMetadata != null) {
				GGSession routingServerSession = sessionManager.getSession(sessionIdMetadata.getSessionId());
				if (routingServerSession != null) {
					pack.setMetadata(null);
					pack.setSession(routingServerSession);
					routingServerSession.send(pack, 0, TimeUnit.MILLISECONDS);
				}
			}
		} catch (Exception e) {
			GGLoggerUtil.getLogger().error("Deserialize metadata Error!", e);
		}
	}

	@Override
	public void handleSendPack(Pack pack) {
		try {
			GGSession session = pack.getSession();
			if (session != null) {
				Object metadata = metadataProvider.provide(session);
				byte[] bytes = serializer.serialize(metadata);
				pack.setMetadata(bytes);
				pack.setSession(null);
			}
		} catch (Exception e) {
			GGLoggerUtil.getLogger().error("Serialize metadata Error!", e);
		}
	}

}
