package com.wuerchat.site.connector.main;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.executor.AbstracteExecutor;
import com.wuerchat.site.connector.handler.ApiRequestHandler;
import com.wuerchat.site.connector.handler.ImAuthenticationHandler;
import com.wuerchat.site.connector.handler.ImHelloHandler;
import com.wuerchat.site.connector.handler.ImMessageHandler;
import com.wuerchat.site.connector.netty.NettyServer;

/**
 * 
 * @author Sam{@link anguoyue254@gmail.com}
 * @since 2017.09.28
 *
 */
public class BootStrap {

	public static void main(String[] args) {

		// load message executor

		// start netty server
		new NettyServer() {

			@Override
			public void loadExecutor(AbstracteExecutor<Command> executor) {
				// 各种IM请求
				executor.addChain("ImHello", new ImHelloHandler());
				executor.addChain("ImAuth", new ImAuthenticationHandler());
				executor.addChain("Im", new ImMessageHandler());
				// 处理各种API请求。
				executor.addChain("ApiUserFriend", new ApiRequestHandler());
				executor.addChain("ApiUserGroup", new ApiRequestHandler());
				executor.addChain("ApiLogin", new ApiRequestHandler());
			}

		}.start("10.11.56.58", 8448);
	}

}
