package com.xzcode.ggcloud.router.common.meta.impl;

import com.xzcode.ggcloud.router.common.meta.RouterSessionIdMetadata;

import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.meta.resolver.IMetadataResolver;
import xzcode.ggserver.core.common.utils.logger.GGLoggerUtil;

/**
 * 用户id元数据解析器
 * 
 * @author zai
 * 2019-12-12 17:52:31
 */
public class RouterSessionIdMetadataResolver implements IMetadataResolver<RouterSessionIdMetadata>{
	
	private ISerializer serializer;

	public RouterSessionIdMetadataResolver(ISerializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public RouterSessionIdMetadata resolve(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			return serializer.deserialize(bytes, RouterSessionIdMetadata.class);
		} catch (Exception e) {
			GGLoggerUtil.getLogger().error("Resolve 'RouterUserIdMetadata' Error!", e);
		}
		return null;
	}

}
