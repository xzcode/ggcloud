package com.xzcode.ggcloud.router.common.meta.impl;

import com.xzcode.ggcloud.router.common.meta.RouterSessionIdMetadata;

import xzcode.ggserver.core.common.message.meta.provider.IMetadataProvider;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 用户id元数据提供者
 * 
 * @author zai
 * 2019-12-12 17:53:05
 */
public class RouterSessionIdMetadataProvider implements IMetadataProvider<RouterSessionIdMetadata>{

	@Override
	public RouterSessionIdMetadata provide(GGSession session) {
		RouterSessionIdMetadata metadata = session.getAttribute(RouterSessionIdMetadata.METADATA_SESSION_KEY, RouterSessionIdMetadata.class);
		if (metadata == null) {
			metadata = new RouterSessionIdMetadata();
			metadata.setSessionId(session.getSessonId());
			session.addAttribute(RouterSessionIdMetadata.METADATA_SESSION_KEY, metadata);
		}
		return metadata;
	}
	
	

}
