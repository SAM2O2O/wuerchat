package com.wuerchat.site.message.service;

import com.wuerchat.common.command.Command;

public class ServiceDispatcher {

	public static boolean dispatch(Command command) {
		// 使用method方法来区分业务功能

		String method = command.getMethod();

		return true;
	}
}
