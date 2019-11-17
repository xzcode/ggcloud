package xzcode.ggcloud.gateway.router.meta;

import xzcode.ggserver.core.common.session.GGSession;

/**
 * 元数据解析器接口
 * 
 * 
 * @author zai
 * 2019-11-11 22:23:56
 */
public interface IMetadataResolver {
	
	/**
	 * 元数据key
	 */
	public static final String METADATA_KEY = "ROUTER_METADATA";
	
	/**
	 * 从session获取元数据
	 * @param session
	 * @return
	 * 
	 * @author zai
	 * 2019-11-11 22:24:03
	 */
	 Object resolveMetadata(GGSession session);
	
}
