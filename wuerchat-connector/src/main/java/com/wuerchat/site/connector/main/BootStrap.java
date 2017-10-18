package com.wuerchat.site.connector.main;

import com.wuerchat.common.chain.AbstractHandlerChain;
import com.wuerchat.common.chain.SimpleHandlerChain;
import com.wuerchat.common.command.Command;
import com.wuerchat.common.executor.AbstracteExecutor;
import com.wuerchat.site.connector.handler.ApiBusinessHandler;
import com.wuerchat.site.connector.handler.AuthenticationHandler;
import com.wuerchat.site.connector.handler.LoginSiteHandler;
import com.wuerchat.site.connector.handler.UserMessageHandler;
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
				AbstractHandlerChain<Command> chain = new SimpleHandlerChain<Command>();
				chain.addHandler(new AuthenticationHandler());
				chain.addHandler(new UserMessageHandler());
				executor.addChain("user-messxage", chain);
				
				executor.addChain("ApiUserFriend", new ApiBusinessHandler());
				executor.addChain("ApiUserGroup", new ApiBusinessHandler());
				executor.addChain("ApiLogin", new ApiBusinessHandler());
			}

		}.start("10.11.56.71", 8448);
	}

}
