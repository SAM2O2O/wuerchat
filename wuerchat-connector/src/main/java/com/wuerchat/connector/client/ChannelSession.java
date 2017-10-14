package com.wuerchat.connector.client;

import com.wuerchat.connector.codec.protocol.RedisCommand;

import io.netty.channel.Channel;

public class ChannelSession {
	private String sitUserId;
	private Channel channel;

	public ChannelSession(Channel channel) {
		this.channel = channel;
	}

	public ChannelSession(String sitUserId, Channel channel) {
		this.sitUserId = sitUserId;
		this.channel = channel;
	}

	public void send(MessagePacket msg) {

		this.channel.writeAndFlush(new RedisCommand().add(10).add("二人消息").add("hello,world!"));

	}

}
