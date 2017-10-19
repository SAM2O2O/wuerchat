package com.wuerchat.site.business.impl;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.zaly.proto.site.UserFriendProto;

public class ApiUserFriendService extends AbstractApiBusiness {

	@Override
	public CommandResponse execute(Command command) {
		return super.executeMethodByReflect(command);
	}

	public CommandResponse getFriendList(Command command) {
		// select friendList from database

		UserFriendProto.FriendSimpleProfile friendProfile0 = UserFriendProto.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1000").setUserName("Sam").setUserPhotoUrl("hello").build();
		UserFriendProto.FriendSimpleProfile friendProfile1 = UserFriendProto.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1001").setUserName("Sam1").setUserPhotoUrl("").build();

		UserFriendProto.FriendSimpleProfile friendProfile2 = UserFriendProto.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1002").setUserName("Sam2").setUserPhotoUrl("").build();

		UserFriendProto.FriendSimpleProfile friendProfile3 = UserFriendProto.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1003").setUserName("Sam3").setUserPhotoUrl("").build();

		UserFriendProto.GetFriendListResponse response = UserFriendProto.GetFriendListResponse.newBuilder()
				.addList(0, friendProfile0).addList(1, friendProfile1)
				// .addList(2, friendProfile2)
				// .addList(3, friendProfile3)
				.build();

		CommandResponse cmdRes = new CommandResponse();
		cmdRes.setVersion(1);
		cmdRes.setAction("_");
		cmdRes.setParams(response.toByteArray());

		System.out.println("return cmdRes to connector");

		return cmdRes;
	}

}
