package com.xzcode.ggcloud.router.client.router.service.impl;

import java.util.concurrent.TimeUnit;
import com.xzcode.ggcloud.router.client.config.RouterClientConfig;
import com.xzcode.ggcloud.router.client.router.meta.RouterUserIdMetadata;
import com.xzcode.ggcloud.router.client.router.service.IRouterPackHandler;
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

	private IMetadataResolver<RouterUserIdMetadata> metadataResolver;
	private IMetadataProvider<RouterUserIdMetadata> metadataProvider;
	private ISerializer serializer;
	private ISessionManager sessionManager;

	

	@SuppressWarnings("unchecked")
	public RouterUsereIdMetadataPackHandler(RouterClientConfig config) {
		this.metadataResolver = (IMetadataResolver<RouterUserIdMetadata>) config.getMetadataResolver();
		this.metadataProvider = (IMetadataProvider<RouterUserIdMetadata>) config.getMetadataProvider();
		this.serializer = config.getRoutingServer().getConfig().getSerializer();
		this.sessionManager = config.getRoutingServer().getConfig().getSessionManager();
	}

	public void handleReceivePack(Pack pack) {
		byte[] metadata = pack.getMetadata();
		try {
			RouterUserIdMetadata userIdMetadata = metadataResolver.resolve(metadata);
			if (userIdMetadata != null) {
				GGSession routingServerSession = sessionManager.getSession(userIdMetadata.getUserId());
				if (routingServerSession != null) {
					pack.setMetadata(null);
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
			Object metadata = metadataProvider.provide(pack.getSession());
			byte[] bytes = serializer.serialize(metadata);
			pack.setMetadata(bytes);
		} catch (Exception e) {
			GGLoggerUtil.getLogger().error("Serialize metadata Error!", e);
		}
	}

}
