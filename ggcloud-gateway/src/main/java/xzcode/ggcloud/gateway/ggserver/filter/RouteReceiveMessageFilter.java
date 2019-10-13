package xzcode.ggcloud.gateway.ggserver.filter;

import org.apache.commons.codec.Charsets;

import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.message.filter.GGBeforeDeserializeFilter;

/**
 * 消息路由
 * 
 * @author zzz
 * 2019-10-09 19:57:21
 */
public class RouteReceiveMessageFilter implements GGBeforeDeserializeFilter{

	@Override
	public boolean doFilter(PackModel data) {
		String action = new String(data.getAction(), Charsets.UTF_8);
		return false;
	}

}
