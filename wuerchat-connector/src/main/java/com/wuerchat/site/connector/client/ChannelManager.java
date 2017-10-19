package com.wuerchat.site.connector.client;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {

	private static Map<String, ChannelSession> clientChannels = new ConcurrentHashMap<String, ChannelSession>();

	private ChannelManager() {

	}

	public static Map<String, ChannelSession> addChannel(String userId, ChannelSession channelSession) {
		clientChannels.put(userId, channelSession);
		return clientChannels;
	}

	public static Map<String, ChannelSession> delChannel(String userId) {
		clientChannels.remove(userId);
		return clientChannels;
	}

	public static ChannelSession getChannelSession(String userId) {
		for (String key : clientChannels.keySet()) {
			if (!userId.equals(key)) {
				System.out.println("key=" + key);
				return clientChannels.get(key);
			}
		}
		return clientChannels.get(userId);
	}

	public static Set<String> getChannelSet() {
		return clientChannels.keySet();
	}

	public static Map<String, ChannelSession> getChannelSession() {
		return clientChannels;
	}

	public static long getChannelsSize() {
		return clientChannels.size();
	}
}