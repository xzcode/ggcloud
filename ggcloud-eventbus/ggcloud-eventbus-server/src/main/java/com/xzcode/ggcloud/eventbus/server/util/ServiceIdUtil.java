package com.xzcode.ggcloud.eventbus.server.util;

import java.util.UUID;

public class ServiceIdUtil {
	
	public static String newServiceId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
