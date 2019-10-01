package xzcode.ggcloud.gateway.ggserver;

import xzcode.ggserver.core.GGServer;
import xzcode.ggserver.core.config.GGConfig;

public class GGServerService {
	
	private GGServer ggServer;
	
	private void init() {
		GGConfig ggServerConfig = new GGConfig();
		ggServer = new GGServer(ggServerConfig);
		

	}
	
}
