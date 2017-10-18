package com.wuerchat.common.constant;

public enum ServiceName {
	None("none"), // none
	ApiUserFriend("ApiUserFriend"), //
	ApiUserGroup("ApiUserGroup"), //
	ApiLogin("ApiLogin");//

	private String serviceName;

	ServiceName(String name) {
		this.serviceName = name;
	}

	public String getName() {
		return this.serviceName;
	}

	public static ServiceName getService(String serviceName) {
		for (ServiceName sn : ServiceName.values()) {
			if (sn.getName().equals(serviceName)) {
				return sn;
			}
		}
		return None;
	}
}
