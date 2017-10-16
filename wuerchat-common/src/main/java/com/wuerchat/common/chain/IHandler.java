package com.wuerchat.common.chain;

public interface IHandler<T> {
	public boolean handle(T t);
}
