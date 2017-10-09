package com.wuerchat.common.chain;

/**
 * 简单的来说，只有返回true，此条消息链才算处理成功
 * 
 * @author Sam
 * @since 2017-09.30
 * @param <T>
 */
public class SimpleHandlerChain<T> extends AbstractHandlerChain<T> {

	@Override
	public boolean handle(T t) {

		for (IHandler<T> handler : getHandlers()) {
			if (handler.handle(t)) {
				return true;
			}
		}

		return false;
	}

}
