package com.wuerchat.site.message.service;

import com.wuerchat.common.command.Command;
import com.wuerchat.site.message.api.IMessageService;

/**
 * 负责接受所有从connector下来的业务请求，请负责分发
 * 
 * @author Sam
 *
 */
public class ImMessageService implements IMessageService {

	public boolean execute(Command command) {

		System.out.println("MessageService execute Command");
		return ServiceDispatcher.dispatch(command);
	}

}
