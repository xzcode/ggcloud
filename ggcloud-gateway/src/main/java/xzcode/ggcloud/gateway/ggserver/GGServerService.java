package xzcode.ggcloud.gateway.ggserver;

import xzcode.ggcloud.gateway.ggserver.pack.GatewayReceivePackHandler;
import xzcode.ggcloud.gateway.ggserver.pack.GatewaySendPackHandler;
import xzcode.ggserver.core.GGServer;
import xzcode.ggserver.core.config.GGConfig;

public class GGServerService {
	
	private GGServer ggServer;
	
	private void init() {
		
		GGConfig config = new GGConfig();
		config.setReceivePackHandler(new GatewayReceivePackHandler());
		config.setSendPackHandler(new GatewaySendPackHandler());
		ggServer = new GGServer(config);
		

	}
	
}
