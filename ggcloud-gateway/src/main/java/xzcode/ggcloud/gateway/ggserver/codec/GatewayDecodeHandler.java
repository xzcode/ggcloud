package xzcode.ggcloud.gateway.ggserver.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import xzcode.ggserver.core.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.handler.codec.IGGDecodeHandler;
import xzcode.ggserver.core.handler.codec.impl.DefaultDecodeHandler;
import xzcode.ggserver.core.message.receive.RequestMessageTask;

public class GatewayDecodeHandler implements IGGDecodeHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayDecodeHandler.class);
	
	@Override
	public void handle(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		int packLen = in.readableBytes();
		
		int reqTagSize = in.readUnsignedShort();
		
		byte[] dataTag = new byte[reqTagSize];
		
		
		in.readBytes(dataTag);
		
		//读取数据体 =  总包长 - 标识长度占用字节 - 标识体占用字节数
		int bodyLen = packLen - REQUEST_TAG_LENGTH_BYTES - reqTagSize;
		byte[] data = null;
		if (bodyLen != 0) {
			
			data = new byte[bodyLen];			
			//读取数据体部分byte数组
			in.readBytes(data);
			
		}
		
		config.getTaskExecutor().submit(new RequestMessageTask(dataTag, data, ctx.channel().attr(DefaultChannelAttributeKeys.SESSION).get(), config));
		
		if(LOGGER.isInfoEnabled()){
        	LOGGER.info("\nReceived binary message  <----,\nchannel:{}\ntag:{}\nbytes-length:{}\ndata:{}", ctx.channel(), dataTag == null ? "" : new String(dataTag), packLen + 4, data == null ? "" : new String(data));
        }
		
	}

}
