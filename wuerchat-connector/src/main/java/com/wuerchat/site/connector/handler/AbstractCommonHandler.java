package com.wuerchat.site.connector.handler;

import com.wuerchat.common.chain.IHandler;

import io.netty.channel.Channel;

public abstract class AbstractCommonHandler<T> implements IHandler<T> {

	protected void closeChannel(Channel channel) {
		channel.close();
	}
}
