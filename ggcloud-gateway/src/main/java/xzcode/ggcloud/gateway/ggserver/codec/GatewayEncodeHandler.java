package xzcode.ggcloud.gateway.ggserver.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import xzcode.ggserver.core.handler.codec.IGGEncodeHandler;
import xzcode.ggserver.core.message.PackModel;

public class GatewayEncodeHandler implements IGGEncodeHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayEncodeHandler.class);

	@Override
	public ByteBuf handle(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		Channel channel = ctx.channel();
		PackModel packModel = (PackModel) msg;
		byte[] tagBytes = packModel.getAction();
		ByteBuf out = null;
		//如果有消息体
		if (packModel.getMessage() != null) {
			
			byte[] bodyBytes = (byte[]) packModel.getMessage();
			
			int packLen = 2 + tagBytes.length + bodyBytes.length;
			
			out = ctx.alloc().buffer(packLen);
			
			out.writeInt(packLen);
			out.writeShort(tagBytes.length);
			out.writeBytes(tagBytes);
			out.writeBytes(bodyBytes);
			if (LOGGER.isInfoEnabled()) {
            	LOGGER.info("\nmessage sended ---> \nchannel:{}\ntag:{}\nbytes-length:{}\ndata:{}", channel, new String(tagBytes), packLen +4 , new String(bodyBytes));
            }
		} else {
		
			//如果没消息体
			
			int packLen = 2 + tagBytes.length;
			
			out = ctx.alloc().buffer(packLen);
			
			out.writeInt(packLen);
			out.writeShort(tagBytes.length);
			out.writeBytes(tagBytes);
			
			if (LOGGER.isInfoEnabled()) {
            	LOGGER.info("\nmessage sended ---> \nchannel:{}\ntag:{}\nbytes-length:{}", channel, new String(tagBytes), packLen + 4);
            }
			
		}
		return out;
	}

}
