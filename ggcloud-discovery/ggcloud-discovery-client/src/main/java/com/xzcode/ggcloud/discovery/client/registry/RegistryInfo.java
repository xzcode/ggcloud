package com.xzcode.ggcloud.discovery.client.registry;

public class RegistryInfo {
	
	private String domain;
	private int port;
	private String autoToken;
	private String zone;
	private String region;
	private boolean down;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAutoToken() {
		return autoToken;
	}
	public void setAutoToken(String autoToken) {
		this.autoToken = autoToken;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	
	
}
