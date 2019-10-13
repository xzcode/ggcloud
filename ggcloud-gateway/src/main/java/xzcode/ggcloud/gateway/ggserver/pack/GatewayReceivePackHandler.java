package xzcode.ggcloud.gateway.ggserver.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzcode.ggcloud.gateway.router.IGatewayRouter;
import xzcode.ggserver.core.common.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.common.handler.pack.IGGReceivePackHandler;
import xzcode.ggserver.core.common.message.PackModel;
import xzcode.ggserver.core.common.message.receive.RequestMessageTask;
import xzcode.ggserver.core.common.session.GGSession;

public class GatewayReceivePackHandler implements IGGReceivePackHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayReceivePackHandler.class);
	
	private IGatewayRouter router;

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
