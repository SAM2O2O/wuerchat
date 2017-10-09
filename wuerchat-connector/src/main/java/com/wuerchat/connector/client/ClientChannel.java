package com.wuerchat.connector.client;

import com.wuerchat.connector.codec.parser.ParserConst;
import com.wuerchat.connector.codec.protocol.ProtocolPacket;

import io.netty.channel.Channel;

public class ClientChannel {
	private String momoid;
	private Channel channel;
	
	public ClientChannel(Channel channel) {
		this.channel = channel;
	}

	public void send(MessagePacket msg) {
		byte[] head = new byte[ParserConst.HEAD_LENGTH];
		head[0] = ((byte) 1);
		head[0] = ((byte) 1);
		head[0] = ((byte) 1);
		head[0] = ((byte) 1);
		head[0] = ((byte) 1);
		head[0] = ((byte) 1);
		head[0] = ((byte) 0);
		head[0] = ((byte) 0);
		head[0] = ((byte) 0);        

		byte[] body = msg.toStr().getBytes();

		// body 序列化
		// body 加密

		ProtocolPacket packet = new ProtocolPacket(head, body);

		this.channel.writeAndFlush(packet);
	}

}
