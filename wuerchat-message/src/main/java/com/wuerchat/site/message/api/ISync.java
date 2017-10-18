package com.wuerchat.site.message.api;

public interface ISync {
	public boolean sync(String id, byte[] body);

	public boolean syncFinish(String id, byte[] body);
}
