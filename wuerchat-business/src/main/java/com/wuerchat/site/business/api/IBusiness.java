package com.wuerchat.site.business.api;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;

public interface IBusiness {

	public CommandResponse process(Command command);

}
