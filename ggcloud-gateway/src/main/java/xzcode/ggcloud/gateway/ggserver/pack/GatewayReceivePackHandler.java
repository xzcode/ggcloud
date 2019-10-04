package xzcode.ggcloud.gateway.ggserver.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzcode.ggcloud.gateway.router.IGGCGatewayRouter;
import xzcode.ggserver.core.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.handler.pack.IGGReceivePackHandler;
import xzcode.ggserver.core.message.PackModel;
import xzcode.ggserver.core.message.receive.RequestMessageTask;
import xzcode.ggserver.core.session.GGSession;

public class GatewayReceivePackHandler implements IGGReceivePackHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayReceivePackHandler.class);
	
	private IGGCGatewayRouter router;

	@Override
	public void handle(PackModel packModel, GGSession session) {
		//config.getTaskExecutor().submit(new RequestMessageTask(packModel, session.getChannel().attr(DefaultChannelAttributeKeys.SESSION).get(), config));
		
		//转接到消息路由器
		router.route(packModel, session);
		
		if(LOGGER.isInfoEnabled()){
        	LOGGER.info("\nReceived binary message  <----,\nchannel:{}\ntag:{}\nbytes-length:{}\ndata:{}", session.getChannel(), packModel.getAction() == null ? "" : new String(packModel.getAction()), packModel.getAction().length + (packModel.getMessage() == null ? 0 : packModel.getMessage().length) + 4, packModel.getMessage() == null ? "" : new String(packModel.getMessage()));
        }
		
	}

}
