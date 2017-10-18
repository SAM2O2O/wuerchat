package com.wuerchat.site.business.impl;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.wuerchat.site.protobuf.business.GetFriendList;

public class ApiUserFriendService extends AbstractApiBusiness {

	@Override
	public CommandResponse execute(Command command) {
		return super.executeMethodByReflect(command);
	}

	public CommandResponse getFriendList(Command command) {
		// select friendList from database

		GetFriendList.FriendSimpleProfile friendProfile0 = GetFriendList.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1000").setUserName("Sam").setUserPhotoUrl("hello").build();
		GetFriendList.FriendSimpleProfile friendProfile1 = GetFriendList.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1001").setUserName("Sam1").setUserPhotoUrl("").build();

		GetFriendList.FriendSimpleProfile friendProfile2 = GetFriendList.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1002").setUserName("Sam2").setUserPhotoUrl("").build();

		GetFriendList.FriendSimpleProfile friendProfile3 = GetFriendList.FriendSimpleProfile.newBuilder()
				.setSiteUserId("1003").setUserName("Sam3").setUserPhotoUrl("").build();

		GetFriendList.ApiFriendListResponse response = GetFriendList.ApiFriendListResponse.newBuilder()
				.addList(0, friendProfile0)
				.addList(1, friendProfile1)
//				.addList(2, friendProfile2)
//				.addList(3, friendProfile3)
				.build();

		CommandResponse cmdRes = new CommandResponse();
		cmdRes.setVersion(1);
		cmdRes.setAction("_");
		cmdRes.setParams(response.toByteArray());

		System.out.println("return cmdRes to connector");

		return cmdRes;
	}

}
