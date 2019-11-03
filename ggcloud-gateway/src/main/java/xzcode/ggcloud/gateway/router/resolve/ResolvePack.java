package xzcode.ggcloud.gateway.router.resolve;

import xzcode.ggserver.core.common.message.Pack;
import xzcode.ggserver.core.common.session.GGSession;

public class ResolvePack {
	
	private String action;
	private Pack pack;
	private GGSession session;
	
	public ResolvePack() {
		super();
	}
	public ResolvePack(String action, Pack pack, GGSession session) {
		super();
		this.action = action;
		this.pack = pack;
		this.session = session;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Pack getPackModel() {
		return pack;
	}
	public void setPackModel(Pack pack) {
		this.pack = pack;
	}
	public GGSession getSession() {
		return session;
	}
	public void setSession(GGSession session) {
		this.session = session;
	}
	
	
}
