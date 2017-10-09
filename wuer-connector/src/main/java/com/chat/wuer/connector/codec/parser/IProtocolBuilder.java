package com.chat.wuer.connector.codec.parser;

import com.chat.wuer.connector.codec.protocol.ProtocolPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public interface IProtocolBuilder {

	public void writeAndOut(Channel ch, ProtocolPacket msg, ByteBuf out);

}
