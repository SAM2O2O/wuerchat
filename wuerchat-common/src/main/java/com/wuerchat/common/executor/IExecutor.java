package com.wuerchat.common.executor;

public interface IExecutor<T> {
	public boolean execute(String name, T t);
}