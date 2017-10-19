package com.wuerchat.site.business.impl;

import java.util.UUID;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.zaly.proto.site.ImLoginProto;
import com.zaly.proto.site.ImRegisterProto;

/**
 * 
 * 执行注册&&登陆
 * 
 * @author Sam
 * @since 2017.10.17
 *
 */
public class ApiLoginService extends AbstractApiBusiness {

	@Override
	public CommandResponse execute(Command command) {
		return super.executeMethodByReflect(command);
	}

	public CommandResponse register(Command command) {

		ImRegisterProto.ApiRegisterResponse response = ImRegisterProto.ApiRegisterResponse.newBuilder()
				.setSiteUserId("10000").build();

		CommandResponse comRes = new CommandResponse();
		comRes.setAction("_");
		comRes.setParams(response.toByteArray());
		return comRes;
	}

	public CommandResponse login(Command command) {

		String sessionId = UUID.randomUUID().toString().substring(0, 16);
		ImLoginProto.ApiLoginResponse response = ImLoginProto.ApiLoginResponse.newBuilder().setSiteUserId("")
				.setUserSessionId(sessionId).build();

		CommandResponse comRes = new CommandResponse();
		comRes.setAction("_");
		comRes.setParams(response.toByteArray());
		return comRes;
	}

}
