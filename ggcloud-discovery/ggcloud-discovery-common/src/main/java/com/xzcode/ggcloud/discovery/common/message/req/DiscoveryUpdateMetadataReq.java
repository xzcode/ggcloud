package com.xzcode.ggcloud.discovery.common.message.req;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新元数据请求
 * 
 * 
 * @author zai
 * 2019-10-05 15:42:07
 */
public class DiscoveryUpdateMetadataReq {
	
	public static final String ACTION = "DISCOVERY.UPDATE.METADATA.REQ";
	
	/**
	 * 元数据map
	 */
	private Map<String, Object> metadata = new HashMap<>();
	
	public void addMetadata(String key, Object value) {
		metadata.put(key, value);
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}
	
}
