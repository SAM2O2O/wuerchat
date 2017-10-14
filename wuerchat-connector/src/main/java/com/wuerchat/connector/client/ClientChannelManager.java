package com.wuerchat.connector.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ClientChannelManager {

	private ConcurrentMap<String, ChannelSession> clientChannels = new ConcurrentHashMap<String, ChannelSession>();

	public void addChannel(){
		
	}
}
