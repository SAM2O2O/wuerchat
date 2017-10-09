package com.chat.wuer.common.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sam
 * @since 2017.09.30
 * @param <T>
 * 
 */
public abstract class AbstractHandlerChain<T> implements IHandler<T> {

	private List<IHandler<T>> handlers = new ArrayList<IHandler<T>>();

	public abstract boolean handle(T t);

	public boolean addHandler(IHandler<T> handler) {
		return handlers.add(handler);
	}

	public List<IHandler<T>> getHandlers() {
		return handlers;
	}
}
