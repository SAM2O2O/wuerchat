package com.chat.wuer.common.executor;

import java.util.HashMap;
import java.util.Map;

import com.chat.wuer.common.chain.IHandler;

public abstract class AbstracteExecutor<T> implements IExecutor<T> {

	protected Map<String, IHandler<T>> executors = new HashMap<String, IHandler<T>>();
	protected Map<String, IHandler<T>> regexExecutors = new HashMap<String, IHandler<T>>();

	public abstract boolean execute(String name, T t);

	public AbstracteExecutor<T> addChain(String name, IHandler<T> chain) {
		executors.put(name, chain);
		return this;
	}

	public AbstracteExecutor<T> addRegexChain(String regexName, IHandler<T> regexChain) {
		regexExecutors.put(regexName, regexChain);
		return this;
	}

	public IHandler<T> getChain(String name) {
		IHandler<T> handler = executors.get(name);
		return handler;
	}

}
