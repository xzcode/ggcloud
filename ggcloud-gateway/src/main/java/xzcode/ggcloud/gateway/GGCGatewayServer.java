package xzcode.ggcloud.gateway;

import xzcode.ggserver.core.GGServer;
import xzcode.ggserver.core.config.GGConfig;

public class GGCGatewayServer {
	
	private GGServer ggServer;
	
	private void init() {
		GGConfig ggServerConfig = new GGConfig();
		ggServer = new GGServer(ggServerConfig);

	}
	
}
