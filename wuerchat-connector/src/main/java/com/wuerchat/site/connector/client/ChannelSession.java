package com.wuerchat.site.connector.client;

import com.wuerchat.site.connector.codec.protocol.RedisCommand;

import io.netty.channel.Channel;

public class ChannelSession {
	private String userId;
	private Channel channel;

	public ChannelSession(Channel channel) {
		this.channel = channel;
	}

	public ChannelSession(String userId, Channel channel) {
		this.userId = userId;
		this.channel = channel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void send(UserMessage msg) {

		this.channel.writeAndFlush(new RedisCommand().add(10).add("二人消息").add("hello,world!"));

	}

}
