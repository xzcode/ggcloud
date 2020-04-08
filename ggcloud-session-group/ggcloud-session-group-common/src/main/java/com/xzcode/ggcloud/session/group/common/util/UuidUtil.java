package com.xzcode.ggcloud.session.group.common.util;

import java.util.UUID;

public class UuidUtil {
	
	public static String newId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
