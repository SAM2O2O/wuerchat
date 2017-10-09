package com.chat.wuer.common.executor;

import com.chat.wuer.common.chain.IHandler;

/**
 * 定义一个简单统一的执行器
 * 
 * @author Sam
 * @since 2017.09.29
 *
 */
public class SimpleExecutor<T> extends AbstracteExecutor<T> {

	@Override
	public boolean execute(String name, T t) {

		IHandler<T> chain = getChain(name);
		return chain.handle(t);

	}

}
