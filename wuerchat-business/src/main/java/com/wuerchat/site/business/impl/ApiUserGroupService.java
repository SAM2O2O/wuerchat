package com.wuerchat.site.business.impl;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.wuerchat.site.protobuf.business.GetGroupList;

public class ApiUserGroupService extends AbstractApiBusiness {

	@Override
	public CommandResponse execute(Command command) {
		// TODO Auto-generated method stub
		return super.executeMethodByReflect(command);
	}

	public CommandResponse getGroupList(Command command) {

		GetGroupList.GroupsimpleProfile groupProfile = GetGroupList.GroupsimpleProfile.newBuilder()
				.setSiteGroupId("5000").setGroupName("Group0").setGroupPhoto("").build();
		
		GetGroupList.GroupsimpleProfile groupProfile1 = GetGroupList.GroupsimpleProfile.newBuilder()
				.setSiteGroupId("5001").setGroupName("Group1").setGroupPhoto("").build();

		GetGroupList.ApiGetGroupsResponse response = GetGroupList.ApiGetGroupsResponse.newBuilder()
				.addList(0, groupProfile)
				.addList(1, groupProfile1).build();
		

		CommandResponse cmdRes = new CommandResponse();
		cmdRes.setVersion(1);
		cmdRes.setAction("_");
		cmdRes.setParams(response.toByteArray());

		System.out.println("return cmdRes to connector");

		return cmdRes;
	}
}
