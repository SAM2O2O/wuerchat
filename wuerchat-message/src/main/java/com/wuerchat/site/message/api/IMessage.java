package com.wuerchat.site.message.api;

public interface IMessage {

	public boolean send(String id, byte[] body);
}
