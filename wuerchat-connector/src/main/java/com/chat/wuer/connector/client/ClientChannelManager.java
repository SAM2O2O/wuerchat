package com.chat.wuer.connector.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ClientChannelManager {

	private ConcurrentMap<String, ClientChannel> clientChannels = new ConcurrentHashMap<String, ClientChannel>();
}
