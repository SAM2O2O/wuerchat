package com.wuerchat.connector.codec.redis;

import java.nio.ByteBuffer;

public class RedisBytesParameter extends AbstractParameter {

	final byte[] val;

	private RedisBytesParameter(byte[] val) {
		this.val = val;
	}

	public static RedisBytesParameter of(byte[] val) {
		return new RedisBytesParameter(val);
	}

	@Override
	public void encode(ByteBuffer buffer) {
		writeBytes(buffer, val);
	}

	static void writeBytes(ByteBuffer buffer, byte[] value) {

		buffer.put((byte) '$');

		RedisIntegerParameter.writeInteger(buffer, value.length);
		buffer.put(CRLF);

		buffer.put(value);
		buffer.put(CRLF);
	}
}
