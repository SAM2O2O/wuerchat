package com.wuerchat.site.business.impl;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;

public interface IApiService {
	public CommandResponse execute(Command command);
}
