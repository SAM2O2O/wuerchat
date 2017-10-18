package com.wuerchat.site.business.service;

import com.wuerchat.common.constant.ServiceName;
import com.wuerchat.site.business.impl.ApiLoginService;
import com.wuerchat.site.business.impl.ApiUserFriendService;
import com.wuerchat.site.business.impl.ApiUserGroupService;
import com.wuerchat.site.business.impl.IApiService;

public class ApiServiceFactory {
	public static IApiService getService(String serviceName) {

		ServiceName nameEnum = ServiceName.getService(serviceName);
		System.out.println("ApiServiceFactory getService by=" + nameEnum);
		switch (nameEnum) {
		case ApiLogin:
			return new ApiLoginService();
		case ApiUserFriend:
			return new ApiUserFriendService();
		case ApiUserGroup:
			return new ApiUserGroupService();
		default:
			System.out.println("ApiServiceFactory.getService error!");
			break;
		}
		return null;
	}

}
