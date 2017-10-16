package com.wuerchat.site.connector.codec.redis;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public abstract class AbstractParameter {
	protected static final Charset UTF8 = Charset.forName("UTF-8");
	protected static final byte[] CRLF = "\r\n".getBytes(UTF8);

	public abstract void encode(ByteBuffer buffer);

	public abstract String getValue();
}
