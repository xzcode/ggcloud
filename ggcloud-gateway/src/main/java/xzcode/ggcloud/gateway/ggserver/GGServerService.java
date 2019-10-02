package xzcode.ggcloud.gateway.ggserver;

import xzcode.ggcloud.gateway.ggserver.codec.GatewayDecodeHandler;
import xzcode.ggcloud.gateway.ggserver.codec.GatewayEncodeHandler;
import xzcode.ggserver.core.GGServer;
import xzcode.ggserver.core.config.GGConfig;

public class GGServerService {
	
	private GGServer ggServer;
	
	private void init() {
		GGConfig config = new GGConfig();
		config.setDecodeHandler(new GatewayDecodeHandler());
		config.setEncodeHandler(new GatewayEncodeHandler());
		ggServer = new GGServer(config);
		

	}
	
}
