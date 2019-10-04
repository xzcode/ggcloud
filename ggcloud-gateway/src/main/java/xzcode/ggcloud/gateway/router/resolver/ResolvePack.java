package xzcode.ggcloud.gateway.router.resolver;

import xzcode.ggserver.core.message.PackModel;
import xzcode.ggserver.core.session.GGSession;

public class ResolvePack {
	
	private String action;
	private PackModel packModel;
	private GGSession session;
	
	public ResolvePack() {
		super();
	}
	public ResolvePack(String action, PackModel packModel, GGSession session) {
		super();
		this.action = action;
		this.packModel = packModel;
		this.session = session;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public PackModel getPackModel() {
		return packModel;
	}
	public void setPackModel(PackModel packModel) {
		this.packModel = packModel;
	}
	public GGSession getSession() {
		return session;
	}
	public void setSession(GGSession session) {
		this.session = session;
	}
	
	
}
