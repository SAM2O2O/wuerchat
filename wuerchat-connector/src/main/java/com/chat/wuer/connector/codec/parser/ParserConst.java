package com.chat.wuer.connector.codec.parser;

import com.chat.wuer.connector.client.ClientChannel;

import io.netty.util.AttributeKey;

public class ParserConst {
	public static int HEAD_LENGTH = 12;

	public static final AttributeKey<ProtocolParser> INPARSER = AttributeKey.valueOf("inParser");

	public static final AttributeKey<ClientChannel> TOCLIENT = AttributeKey.valueOf("client");
}
