package com.wuerchat.connector.codec.redis;

import java.nio.ByteBuffer;

public class RedisDoubleParameter extends AbstractParameter {
	final double val;

	private RedisDoubleParameter(double val) {
		this.val = val;
	}

	public static RedisDoubleParameter of(double val) {
		return new RedisDoubleParameter(val);
	}

	@Override
	public void encode(ByteBuffer target) {
		RedisStringParameter.writeString(target, Double.toString(val));
	}
}
