package com.wuerchat.site.connector.handler;

import java.util.HashMap;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.wuerchat.common.command.Command;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.zaly.proto.core.CoreProto;
import com.zaly.proto.site.ImHelloProto;

import io.netty.channel.Channel;

/**
 * 握手交换版本号处理
 * 
 * @author Sam
 *
 * @param <Command>
 */
public class ImHelloHandler extends AbstractCommonHandler<Command> {

	public boolean handle(Command cmd) {

		System.out.println("===HelloHandler====");

		ChannelSession channelSession = cmd.getField("channel_session", ChannelSession.class);

		if (channelSession == null) {
			System.out.println("ApiBusinessHandler error.....");
		}

		process(channelSession.getChannel(), cmd);

		System.out.println("channelAddress=" + channelSession.getChannel().toString());

		return true;
	}

	private void process(Channel channel, Command command) {

		System.out.println("execute api request from client");
		try {
			CoreProto.TransportPackageData packageData = CoreProto.TransportPackageData
					.parseFrom(ByteString.copyFrom(command.getParams().getBytes()));
			ImHelloProto.ImHelloRequest request = ImHelloProto.ImHelloRequest.parseFrom(packageData.getData());

			// 兼容客户端版本
			String client_version = request.getClientVersion();
			System.out.println("get client_version=" + client_version);

		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			System.out.println("process Im Hello request error!" + e.getMessage());
		}

		ImHelloProto.ImHelloResponse response = ImHelloProto.ImHelloResponse.newBuilder().setSiteVersion("0.0.1")
				.build();

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(response.toByteArray()))
				.build();

		channel.writeAndFlush(new RedisCommand().add(1).add("_").add(data.toByteArray()));

	}

}
