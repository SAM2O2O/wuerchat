package com.chat.wuer.common.chain;

public interface IHandler<T> {
	boolean handle(T t);
}
