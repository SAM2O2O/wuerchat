package com.wuerchat.connector.codec.parser;

import com.wuerchat.connector.codec.protocol.RedisCommand;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public interface IProtocolBuilder {

	public void writeAndOut(Channel ch, RedisCommand cmd, ByteBuf out);

}
