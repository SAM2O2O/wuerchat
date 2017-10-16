package com.wuerchat.site.connector.codec.redis;

import java.nio.ByteBuffer;

public class RedisStringParameter extends AbstractParameter {

	public final String val;

	public RedisStringParameter(String val) {
		this.val = val;
	}

	public static RedisStringParameter of(String val) {
		return new RedisStringParameter(val);
	}

	@Override
	public void encode(ByteBuffer target) {
		writeString(target, val);
	}

	public static void writeString(ByteBuffer target, String value) {
		target.put((byte) '$');
		byte[] valueByte = value.getBytes(UTF8);
		RedisIntegerParameter.writeInteger(target, valueByte.length);
		target.put(CRLF);
		target.put(valueByte);
		target.put(CRLF);
	}

	@Override
	public String getValue() {
		return val;
	}
}
