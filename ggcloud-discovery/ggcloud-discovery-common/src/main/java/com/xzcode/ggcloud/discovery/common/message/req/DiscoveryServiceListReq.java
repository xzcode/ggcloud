package com.xzcode.ggcloud.discovery.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

public class DiscoveryServiceListReq  implements IMessage{
	
	public static final String ACTION = "DISCOVERY.SERVICE.LIST.REQ";

	@Override
	public String getActionId() {
		return ACTION;
	}
	
}
