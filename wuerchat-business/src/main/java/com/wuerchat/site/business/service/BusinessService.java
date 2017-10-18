package com.wuerchat.site.business.service;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.wuerchat.site.business.api.IBusiness;

/**
 * 处理业务逻辑
 * 
 * @author Sam
 * @since 2017.10.16
 *
 */
public class BusinessService implements IBusiness {

	public CommandResponse process(Command command) {
		System.out.println("BusinessService=" + command.toString());

		return ApiServiceFactory.getService(command.getService()).execute(command);
	}

}
