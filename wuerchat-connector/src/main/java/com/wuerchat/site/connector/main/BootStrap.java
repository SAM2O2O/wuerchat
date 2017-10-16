package com.wuerchat.site.connector.main;

import com.wuerchat.common.chain.AbstractHandlerChain;
import com.wuerchat.common.chain.SimpleHandlerChain;
import com.wuerchat.common.command.Command;
import com.wuerchat.common.executor.AbstracteExecutor;
import com.wuerchat.site.connector.handler.AuthForUserHandler;
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
				chain.addHandler(new AuthForUserHandler<Command>());
				chain.addHandler(new AuthForUserHandler<Command>());

				executor.addChain("test", chain);
			}

		}.start();
	}

}
