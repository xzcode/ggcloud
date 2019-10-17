package com.xzcode.ggcloud.discovery.client.registry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 注册中心管理器
 * 
 * 
 * @author zai
 * 2019-10-05 13:49:56
 */
public class RegistryManager {
	
	
	//注册中心信息
	private List<RegistryInfo> registryInfos = new CopyOnWriteArrayList<>();
	
	/**
	 * 所有注册中心都已失效
	 */
	private boolean allRegistriesDown;
	
	
	
	/**
	 * 随机获取一个注册中心
	 * @return
	 * 
	 * @author zai
	 * 2019-10-05 13:49:47
	 */
	public RegistryInfo getRandomRegistry() {
		if (registryInfos.isEmpty()) {
			return null;
		}
		if (registryInfos.size() == 1) {
			return registryInfos.get(0);
		}
		return registryInfos.get(ThreadLocalRandom.current().nextInt(registryInfos.size()));
	}
}
