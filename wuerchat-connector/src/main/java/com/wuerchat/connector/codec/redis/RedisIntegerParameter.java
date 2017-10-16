package com.wuerchat.connector.codec.redis;

import java.nio.ByteBuffer;

public class RedisIntegerParameter extends AbstractParameter {
	final long val;

	private RedisIntegerParameter(long val) {
		this.val = val;
	}

	public static RedisIntegerParameter of(long val) {

		if (val >= 0 && val < IntegerCache.cache.length) {
			return IntegerCache.cache[(int) val];
		}

		if (val < 0 && -val < IntegerCache.cache.length) {
			return IntegerCache.negativeCache[(int) -val];
		}

		return new RedisIntegerParameter(val);
	}

	@Override
	public void encode(ByteBuffer target) {
		RedisStringParameter.writeString(target, Long.toString(val));
	}

	public static void writeInteger(ByteBuffer target, long value) {
		if (value < 10) {
			target.put((byte) ('0' + value));
			return;
		}

		String asString = Long.toString(value);

		for (int i = 0; i < asString.length(); i++) {
			target.put((byte) asString.charAt(i));
		}

	}

	static class IntegerCache {

		static final RedisIntegerParameter cache[];
		static final RedisIntegerParameter negativeCache[];

		static {
			int high = Integer.getInteger("biz.paluch.redis.CommandArgs.IntegerCache", 128);
			cache = new RedisIntegerParameter[high];
			negativeCache = new RedisIntegerParameter[high];
			for (int i = 0; i < high; i++) {
				cache[i] = new RedisIntegerParameter(i);
				negativeCache[i] = new RedisIntegerParameter(-i);
			}
		}
	}

	@Override
	public String getValue() {
		return String.valueOf(val);
	}
}
