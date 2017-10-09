package com.wuerchat.common.chain;

public interface IHandler<T> {
	boolean handle(T t);
}
