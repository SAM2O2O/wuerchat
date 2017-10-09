package com.wuerchat.connector.codec.parser;

import com.wuerchat.connector.codec.protocol.ProtocolPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ProtocolBuilder implements IProtocolBuilder {

	private ProtocolBuilder() {
	}

	public static IProtocolBuilder getInstance() {
		return SingletonHolder.instance;
	}

	interface SingletonHolder {
		IProtocolBuilder instance = new ProtocolBuilder();
	}

	public void writeAndOut(Channel ch, ProtocolPacket pack, ByteBuf out) {
		byte[] head = pack.getHead();
		byte[] body = pack.getBody();
		
		// #TODO 加密

		out.writeByte((byte) 1);
		if (head.length == 0) {
			out.writeByte((byte) 1);
		} else {
			if (head.length > 0) {
				out.writeBytes(head);
				if (body.length > 0) {
					out.writeBytes(body);
				}
			}
		}
	}

}
