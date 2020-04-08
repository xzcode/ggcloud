package xzcode.ggserver.core.common.prefebs.sessiongroup;

import java.nio.charset.Charset;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import xzcode.ggserver.core.common.channel.DefaultChannelAttributeKeys;
import xzcode.ggserver.core.common.config.GGConfig;
import xzcode.ggserver.core.common.handler.serializer.ISerializer;
import xzcode.ggserver.core.common.message.request.Request;
import xzcode.ggserver.core.common.message.request.action.IRequestMessageHandler;
import xzcode.ggserver.core.common.message.response.Response;
import xzcode.ggserver.core.common.message.response.support.IMakePackSupport;
import xzcode.ggserver.core.common.prefebs.pingpong.model.GGPingPongInfo;
import xzcode.ggserver.core.common.prefebs.pingpong.model.GGPing;
import xzcode.ggserver.core.common.prefebs.pingpong.model.GGPong;
import xzcode.ggserver.core.common.prefebs.sessiongroup.model.GGSessionGroupRegisterReq;
import xzcode.ggserver.core.common.session.GGSession;
import xzcode.ggserver.core.common.session.group.manager.GGSessionGroupManager;

/**
 * 内置ping处理器
 * 
 * @author zai
 * 2020-01-16 17:04:11
 */
public class GGSessionGroupRegisterReqHandler implements IRequestMessageHandler<GGSessionGroupRegisterReq> , IMakePackSupport{
	
	protected GGConfig config;
	
	protected static final AttributeKey<GGPingPongInfo> PING_PONG_INFO_KEY = AttributeKey.valueOf(DefaultChannelAttributeKeys.PING_INFO);
	
	public GGSessionGroupRegisterReqHandler(GGConfig config) {
		this.config = config;
	}

	@Override
	public void handle(Request<GGSessionGroupRegisterReq> request) {
		GGSessionGroupRegisterReq req = request.getMessage();
		String groupId = req.getGroupId();
		GGSession session = request.getSession();
		GGSessionGroupManager sessionGroupManager = config.getSessionGroupManager();
		
		sessionGroupManager.addSession(groupId, session);
		
	}



	@Override
	public Charset getCharset() {
		return config.getCharset();
	}



	@Override
	public ISerializer getSerializer() {
		return config.getSerializer();
	}


}
