package com.wuerchat.site.connector.codec.parser;

import com.wuerchat.site.connector.client.ChannelSession;

import io.netty.util.AttributeKey;

public class ParserConst {
	public static int HEAD_LENGTH = 12;

	public static final AttributeKey<ProtocolParser> INPARSER = AttributeKey.valueOf("inParser");

	public static final AttributeKey<ChannelSession> CHANNELSESSION = AttributeKey.valueOf("channelSession");
}
