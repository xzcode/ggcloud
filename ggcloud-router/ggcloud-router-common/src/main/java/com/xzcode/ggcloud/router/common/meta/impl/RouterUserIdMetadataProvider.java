package com.xzcode.ggcloud.router.common.meta.impl;

import com.xzcode.ggcloud.router.common.meta.RouterUserIdMetadata;

import xzcode.ggserver.core.common.message.meta.provider.IMetadataProvider;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 用户id元数据提供者
 * 
 * @author zai
 * 2019-12-12 17:53:05
 */
public class RouterUserIdMetadataProvider implements IMetadataProvider<RouterUserIdMetadata>{

	@Override
	public RouterUserIdMetadata provide(GGSession session) {
		RouterUserIdMetadata metadata = session.getAttribute(RouterUserIdMetadata.METADATA_SESSION_KEY, RouterUserIdMetadata.class);
		return metadata;
	}
	
	

}
