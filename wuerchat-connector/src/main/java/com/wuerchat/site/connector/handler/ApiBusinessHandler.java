package com.wuerchat.site.connector.handler;

import com.wuerchat.common.command.Command;
import com.wuerchat.site.business.api.IBusiness;
import com.wuerchat.site.business.service.BusinessService;

/**
 * 使用TCP处理API请求
 * 
 * @author Sam
 *
 * @param <Command>
 */
public class ApiBusinessHandler extends AbstractCommonHandler<Command> {

	public boolean handle(Command cmd) {
		// 调用消息
		IBusiness business = new BusinessService();
		System.out.println("ApiBusinessHandler");
		business.process(cmd);
		return true;
	}

}
