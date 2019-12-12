package com.xzcode.ggcloud.router.client.router.meta.impl;

import com.xzcode.ggcloud.router.client.router.meta.RouterUserIdMetadata;

import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.meta.resolver.IMetadataResolver;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 用户id元数据解析器
 * 
 * @author zai
 * 2019-12-12 17:52:31
 */
public class RouterUserIdMetadataResolver implements IMetadataResolver<RouterUserIdMetadata>{
	
	private ISerializer serializer;

	public RouterUserIdMetadataResolver(ISerializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public RouterUserIdMetadata resolve(byte[] bytes) {
		try {
			return serializer.deserialize(bytes, RouterUserIdMetadata.class);
		} catch (Exception e) {
			GGLoggerUtil.getLogger().error("Resolve 'RouterUserIdMetadata' Error!", e);
		}
		return null;
	}

}
