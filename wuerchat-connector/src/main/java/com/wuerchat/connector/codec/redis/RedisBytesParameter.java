package com.wuerchat.connector.codec.redis;

import java.nio.ByteBuffer;

public class RedisBytesParameter extends AbstractParameter {

	final byte[] value;

	private RedisBytesParameter(byte[] value) {
		this.value = value;
	}

	public static RedisBytesParameter of(byte[] value) {
		return new RedisBytesParameter(value);
	}

	@Override
	public void encode(ByteBuffer buffer) {
		writeBytes(buffer, value);
	}

	static void writeBytes(ByteBuffer buffer, byte[] value) {

		buffer.put((byte) '$');

		RedisIntegerParameter.writeInteger(buffer, value.length);
		buffer.put(CRLF);

		buffer.put(value);
		buffer.put(CRLF);
	}

	@Override
	public String getValue() {
		return new String(value);
	}
}
