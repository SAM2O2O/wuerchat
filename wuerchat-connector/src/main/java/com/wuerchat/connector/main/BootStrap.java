package com.wuerchat.connector.main;

import com.chrome.chat.common.chain.AbstractHandlerChain;
import com.chrome.chat.common.chain.SimpleHandlerChain;
import com.chrome.chat.common.command.Command;
import com.chrome.chat.common.executor.AbstracteExecutor;
import com.wuerchat.connector.handler.AuthForUserHandler;
import com.wuerchat.connector.netty.BimNettyServer;

/**
 * 
 * @author Sam{@link anguoyue254@gmail.com}
 *
 */
public class BootStrap {

	public static void main(String[] args) {

		new BimNettyServer() {

			@Override
			public void loadExecutor(AbstracteExecutor<Command> executor) {
				AbstractHandlerChain<Command> chain = new SimpleHandlerChain<Command>();
				chain.addHandler(new AuthForUserHandler<Command>());
				chain.addHandler(new AuthForUserHandler<Command>());

				executor.addChain("msgAction", chain);
			}

		}.start();
	}

}
