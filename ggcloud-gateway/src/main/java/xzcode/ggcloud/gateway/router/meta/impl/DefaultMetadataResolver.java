package xzcode.ggcloud.gateway.router.meta.impl;

import xzcode.ggcloud.gateway.router.meta.IMetadata;
import xzcode.ggcloud.gateway.router.meta.IMetadataResolver;
import xzcode.ggserver.core.common.session.GGSession;

/**
 * 默认元数据解析器
 * 
 * 
 * @author zai
 * 2019-11-11 22:23:33
 */
public class DefaultMetadataResolver implements IMetadataResolver{
	
	/**
	 * 元数据key
	 */
	private static final String METADATA_KEY = "ROUTER_METADATA";

	@Override
	public IMetadata resolveMetadata(GGSession session) {
		return session.getAttribute(METADATA_KEY, IMetadata.class);
	}

}
