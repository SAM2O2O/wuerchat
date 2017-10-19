package com.wuerchat.site.connector.handler;

import java.util.HashMap;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.wuerchat.common.command.Command;
import com.wuerchat.site.connector.client.ChannelManager;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.wuerchat.site.message.api.IMessageService;
import com.wuerchat.site.message.service.ImMessageService;
import com.zaly.proto.client.ImToClientMsgProto;
import com.zaly.proto.core.CoreProto;
import com.zaly.proto.core.CoreProto.MsgType;
import com.zaly.proto.site.ImAuthProto;
import com.zaly.proto.site.ImToSiteMsgProto;

import io.netty.channel.Channel;

/**
 * 这里负责下发消息至message模块进行处理
 * 
 * @author sam
 *
 * @param <Command>
 */
public class ImMessageHandler extends AbstractCommonHandler<Command> {

	public boolean handle(Command command) {
		System.out.println("========MessageHandler========");

		ChannelSession channelSession = command.getField("channel_session", ChannelSession.class);

		System.out.println("ImMessageHandler.command=" + command.toString());
		String method = command.getMethod();
		if (method.equalsIgnoreCase("toSiteMsg")) {
			try {
				ImToSiteMsgProto.ToSiteMsgRequest request = ImToSiteMsgProto.ToSiteMsgRequest
						.parseFrom(command.getParams().getBytes());

				int type = request.getType().getNumber();

				switch (type) {
				case 3:
					String site_user_id = request.getText().getSiteFriendId();
					String site_friend_id = request.getText().getSiteFriendId();
					System.out.println("message TEXT site_user_id = " + site_user_id);
					System.out.println("message TEXT site_friend_id = " + site_friend_id);

					// 给friend用户发送消息
					Channel friend_channel = ChannelManager.getChannelSession(site_friend_id).getChannel();

					if (friend_channel == null) {
						friend_channel = channelSession.getChannel();
						System.out.println("user friend is offline! userself instead!");
					}

					response(friend_channel, command, site_user_id, site_friend_id);

					break;
				case 4:
					break;
				default:
					System.out.println("message type error!");
				}

			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equalsIgnoreCase("sync")) {

		} else if (method.equalsIgnoreCase("syncFinish")) {

		}

		// A发送消息B，这里直接转发

		// IMessageService messageService = new ImMessageService();
		//
		// return messageService.execute(command);

		return true;

	}

	private void response(Channel channel, Command command, String from, String to) {

		System.out.println("execute toClientMsg Response");
		// invoke interface of business
		CoreProto.MsgText text = CoreProto.MsgText.newBuilder().setMsgId("1001001").setSiteUserId(from)
				.setSiteFriendId(to).setText(ByteString.copyFromUtf8("You are youn g!xx")).build();
		CoreProto.MsgText text1 = CoreProto.MsgText.newBuilder().setMsgId("1001002").setSiteUserId(from)
				.setSiteFriendId(to).setText(ByteString.copyFromUtf8("You are not youn g!xx")).build();

		ImToClientMsgProto.MsgWithPointer msg = ImToClientMsgProto.MsgWithPointer.newBuilder().setType(MsgType.TEXT)
				.setPointer(100).setText(text).build();
		ImToClientMsgProto.MsgWithPointer msg1 = ImToClientMsgProto.MsgWithPointer.newBuilder().setType(MsgType.TEXT)
				.setPointer(101).setText(text1).build();

		ImToClientMsgProto.ToClientMsgRequest request = ImToClientMsgProto.ToClientMsgRequest.newBuilder()
				.setList(0, msg).setList(1, msg1).build();

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(request.toByteArray()))
				.build();

		channel.writeAndFlush(new RedisCommand().add(1).add("_").add(data.toByteArray()));

	}

}
