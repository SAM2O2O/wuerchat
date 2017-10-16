package com.wuerchat.connector.client;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelManager {

	private ConcurrentMap<String, ChannelSession> clientChannels = new ConcurrentHashMap<String, ChannelSession>();

	private ChannelManager() {

	}

	public static ChannelManager getInstance() {
		return SingletonHolder.instance;
	}

	static class SingletonHolder {
		private static ChannelManager instance = new ChannelManager();
	}

	public ChannelManager addChannel(String userId, ChannelSession channelSession) {
		clientChannels.put(userId, channelSession);
		return this;
	}

	public ChannelManager delChannel(String userId) {
		clientChannels.remove(userId);
		return this;
	}

	public ChannelSession getChannelSession(String userId) {
		for (String key : clientChannels.keySet()) {
			if (!userId.equals(key)) {
				System.out.println("key=" + key);
				return clientChannels.get(key);
			}
		}
		return clientChannels.get(userId);
	}

	public Set<String> getChannelSet() {
		return clientChannels.keySet();
	}
}
