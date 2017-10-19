package com.wuerchat.site.connector.handler;

import java.util.HashMap;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.wuerchat.common.command.Command;
import com.wuerchat.site.connector.client.ChannelManager;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.zaly.proto.core.CoreProto;
import com.zaly.proto.site.ImAuthProto;

import io.netty.channel.Channel;

/**
 * auth 处理
 * 
 * @author Sam
 *
 * @param <Command>
 */
public class ImAuthenticationHandler extends AbstractCommonHandler<Command> {

	public boolean handle(Command cmd) {

		System.out.println("===AuthHandler====");

		ChannelSession channelSession = cmd.getField("channel_session", ChannelSession.class);

		if (channelSession == null) {
			System.out.println("ApiBusinessHandler error.....");
		}

		// add channelSession to manager

		process(channelSession, cmd);

		response(channelSession.getChannel(), cmd);

		deliverBusiness(cmd);
		return true;
	}

	private void process(ChannelSession channelSession, Command command) {
		try {
			CoreProto.TransportPackageData packageData = CoreProto.TransportPackageData
					.parseFrom(ByteString.copyFrom(command.getParams().getBytes()));
			ImAuthProto.ImAuthRequest request = ImAuthProto.ImAuthRequest.parseFrom(packageData.getData());

			String site_user_id = request.getSiteUserId();
			String site_session_id = request.getSiteSessionId();

			System.out.println("site_user_id = " + site_user_id);
			System.out.println("site_session_id = " + site_session_id);

			ChannelManager.addChannel(site_user_id, channelSession);

			System.out.println("Im_auth  success! channelSize=" + ChannelManager.getChannelsSize());

		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("process auth error!......");
		}
	}

	private void response(Channel channel, Command command) {

		System.out.println("execute ImAuth Response");
		// invoke interface of business

		ImAuthProto.ImAuthResponse response = ImAuthProto.ImAuthResponse.newBuilder().setSiteServer("127.0.0.1:8080")
				.build();

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(response.toByteArray()))
				.build();

		channel.writeAndFlush(new RedisCommand().add(1).add("_").add(data.toByteArray()));

	}

	private void deliverBusiness(Command command) {

		System.out.println("auth 请求已经完成，继续下发到Auth业务处理");

	}

}
