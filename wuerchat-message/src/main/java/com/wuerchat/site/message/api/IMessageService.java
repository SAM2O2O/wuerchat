package com.wuerchat.site.message.api;

import com.wuerchat.common.command.Command;

public interface IMessageService {

	public boolean execute(Command command);
}
