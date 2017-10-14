package com.wuerchat.connector.codec.protocol;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.wuerchat.connector.codec.redis.AbstractParameter;
import com.wuerchat.connector.codec.redis.RedisBytesParameter;
import com.wuerchat.connector.codec.redis.RedisDoubleParameter;
import com.wuerchat.connector.codec.redis.RedisIntegerParameter;
import com.wuerchat.connector.codec.redis.RedisStringParameter;

/**
 * 
 * @author Sam
 * @since 2017.10.13
 * 
 */
public class RedisCommand {

	private static final Charset ASCII = Charset.forName("US-ASCII");
	private static final Charset UTF8 = Charset.forName("UTF-8");
	private static final byte[] CRLF = "\r\n".getBytes(ASCII);

	final List<AbstractParameter> arguments = new ArrayList<AbstractParameter>();

	public RedisCommand() {

	}

	public RedisCommand add(int n) {
		arguments.add(RedisIntegerParameter.of(n));
		return this;
	}

	public RedisCommand add(String s) {

		arguments.add(RedisStringParameter.of(s));
		return this;
	}

	public RedisCommand add(long n) {

		arguments.add(RedisIntegerParameter.of(n));
		return this;
	}

	public RedisCommand add(double n) {

		arguments.add(RedisDoubleParameter.of(n));
		return this;
	}

	public RedisCommand add(byte[] value) {

		arguments.add(RedisBytesParameter.of(value));
		return this;
	}

	public void encode(ByteBuffer buf) {
		buf.put((byte) '*');
		RedisIntegerParameter.writeInteger(buf, arguments.size());
		buf.put(CRLF);
		for (AbstractParameter argument : arguments) {
			argument.encode(buf);
		}

	}

}
