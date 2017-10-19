package com.wuerchat.site.connector.codec.parser;

import java.nio.ByteBuffer;

import com.wuerchat.site.connector.codec.protocol.RedisCommand;

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

	
	public void writeAndOut(Channel ch, RedisCommand redisCmd, ByteBuf out) {
		int byteSize = redisCmd.getByteSize();
		System.out.println("byteSize = " + byteSize);
		ByteBuffer byteBuffer = ByteBuffer.allocate(byteSize);
		redisCmd.encode(byteBuffer);
		byte[] bytes = byteBuffer.array();
		out.writeBytes(bytes);

	}

}
