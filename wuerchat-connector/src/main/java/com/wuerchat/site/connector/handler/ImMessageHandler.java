package com.wuerchat.site.connector.handler;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

import com.google.protobuf.ByteString;
import com.wuerchat.common.command.Command;
import com.wuerchat.site.connector.client.ChannelManager;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.zaly.proto.client.ImPshProto;
import com.zaly.proto.client.ImToClientMsgProto;
import com.zaly.proto.core.CoreProto;
import com.zaly.proto.core.CoreProto.MsgType;
import com.zaly.proto.site.ImSyncProto;
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

		System.out.println("ImMessageHandler.length=" + command.getParams().length() + "=" + command.toString());

		for (byte cub : command.getParams().getBytes()) {
			System.out.print("," + (int) cub);
		}
		
		String method = command.getMethod();
		if (method.equalsIgnoreCase("toSiteMsg")) {
			try {
				try {
					
					CoreProto.TransportPackageData packageData = CoreProto.TransportPackageData
							.parseFrom(ByteString.copyFrom(command.getParams().getBytes()));

					ImToSiteMsgProto.ToSiteMsgRequest request = ImToSiteMsgProto.ToSiteMsgRequest
							.parseFrom(packageData.getData());

					int type = request.getType().getNumber();
					System.out.println("inner type = " + type);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("error...........");
				}
				int type = 3;
				System.out.println("MsgType = " + type);

				switch (type) {
				case 3:
					// String site_user_id =
					// request.getText().getSiteFriendId();
					// String site_friend_id =
					// request.getText().getSiteFriendId();

					String site_user_id = "10001";
					String site_friend_id = "2222";

					System.out.println("message TEXT site_user_id = " + site_user_id);
					System.out.println("message TEXT site_friend_id = " + site_friend_id);

					// 给friend用户发送消息
					Channel friend_channel = ChannelManager.getChannelSession(site_friend_id).getChannel();

					if (friend_channel == null) {
						friend_channel = channelSession.getChannel();
						System.out.println("user friend is offline! userself instead!");
					}

					//
					responseRet(friend_channel, command, site_user_id, site_friend_id);

					responsePsh(friend_channel, command, site_user_id, site_friend_id);

					break;
				case 4:
					break;
				default:
					System.out.println("message type error!");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equalsIgnoreCase("sync")) {
			System.out.println("action = Sync");

			try {

				CoreProto.TransportPackageData packageData = CoreProto.TransportPackageData
						.parseFrom(ByteString.copyFromUtf8(command.getParams()));

				ImSyncProto.ImSyncRequest syncRequest = ImSyncProto.ImSyncRequest.parseFrom(packageData.getData());

				System.out.println("分析sync");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("分析sync失败" + e.getMessage());
			}

			String site_user_id = "10001";
			String site_friend_id = "10000";

			System.out.println("message TEXT site_user_id = " + site_user_id);
			System.out.println("message TEXT site_friend_id = " + site_friend_id);

			// 给friend用户发送消息
			// Channel friend_channel =
			// ChannelManager.getChannelSession(site_friend_id).getChannel();
			Channel friend_channel = null;
			if (friend_channel == null) {
				friend_channel = channelSession.getChannel();
				System.out.println("user friend is offline! userself instead!");
			}

			responseMsg(friend_channel, command, site_user_id, site_friend_id);

		} else if (method.equalsIgnoreCase("syncFinish")) {

			System.out.println("action  = SyncFinish");
		}

		// A发送消息B，这里直接转发

		// IMessageService messageService = new ImMessageService();
		//
		// return messageService.execute(command);

		return true;

	}

	private void responsePsh(Channel channel, Command command, String from, String to) {

		System.out.println("execute toClientMsg psh");
		// invoke interface of business
		ImPshProto.ImPshRequest pshRequest = ImPshProto.ImPshRequest.newBuilder().build();

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(pshRequest.toByteArray()))
				.build();

		channel.writeAndFlush(new RedisCommand().add(1).add("Im.psh").add(data.toByteArray()));

	}

	private void responseRet(Channel channel, Command command, String from, String to) {

		System.out.println("execute toClientMsg Ret");
		// invoke interface of business
		CoreProto.MsgStatus status = CoreProto.MsgStatus.newBuilder().setMsgId("1001001").setMsgStatus(1).build();

		ImToClientMsgProto.MsgWithPointer statusMsg = ImToClientMsgProto.MsgWithPointer.newBuilder()
				.setType(MsgType.MSG_STATUS).setStatus(status).build();

		ImToClientMsgProto.ToClientMsgRequest request = ImToClientMsgProto.ToClientMsgRequest.newBuilder()
				.addList(0, statusMsg).build();

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(request.toByteArray()))
				.build();

		channel.writeAndFlush(new RedisCommand().add(1).add("Im.toClientMsg").add(data.toByteArray()));

	}

	private void responseMsg(Channel channel, Command command, String from, String to) {

		System.out.println("execute toClientMsg Response");

		String msgId = UUID.randomUUID().toString().substring(0, 8);

		// invoke interface of business
		CoreProto.MsgText text = CoreProto.MsgText.newBuilder().setMsgId(msgId + "1").setSiteUserId(from)
				.setSiteFriendId(to).setText(ByteString.copyFromUtf8("You are youn g!xx")).build();
		CoreProto.MsgText text1 = CoreProto.MsgText.newBuilder().setMsgId(msgId + "2").setSiteUserId(from)
				.setSiteFriendId(to).setText(ByteString.copyFromUtf8("You are not youn g!xx")).build();
		CoreProto.MsgFinish msgFinishPointer = CoreProto.MsgFinish.newBuilder().build();

		ImToClientMsgProto.MsgWithPointer msg = ImToClientMsgProto.MsgWithPointer.newBuilder().setType(MsgType.TEXT)
				.setPointer(100).setText(text).build();
		ImToClientMsgProto.MsgWithPointer msg1 = ImToClientMsgProto.MsgWithPointer.newBuilder().setType(MsgType.TEXT)
				.setPointer(101).setText(text1).build();
		ImToClientMsgProto.MsgWithPointer msgFinish = ImToClientMsgProto.MsgWithPointer.newBuilder()
				.setType(MsgType.MSG_FINISH).setFinish(msgFinishPointer).build();

		ImToClientMsgProto.ToClientMsgRequest request = ImToClientMsgProto.ToClientMsgRequest.newBuilder()
				.addList(0, msg).addList(1, msg1).addList(2, msgFinish).build();

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(request.toByteArray()))
				.build();

		channel.writeAndFlush(new RedisCommand().add(1).add("Im.toClientMsg").add(data.toByteArray()));

	}

}
