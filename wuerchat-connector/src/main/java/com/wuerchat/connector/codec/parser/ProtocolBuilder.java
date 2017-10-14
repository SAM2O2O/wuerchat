package com.wuerchat.connector.codec.parser;

import java.nio.ByteBuffer;

import com.wuerchat.connector.codec.protocol.RedisCommand;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ProtocolBuilder implements IProtocolBuilder {

	private ProtocolBuilder() {
	}

	public static IProtocolBuilder getInstance() {
		return SingletonHolder.instance;
	}

	interface SingletonHolder {
		IProtocolBuilder instance = new ProtocolBuilder();
	}

	public void writeAndOut(Channel ch, RedisCommand cmd, ByteBuf out) {
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(100);
		cmd.encode(byteBuffer);
		byte[] bytes = byteBuffer.array();

		out.writeBytes(bytes);

	}

}
