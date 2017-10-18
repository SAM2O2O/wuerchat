package com.wuerchat.common.command;

/**
 * 处理完成后的消息载体
 * 
 * @author Sam
 * @since 2017-09.30
 */
public class Command {
	private String service;
	private String method;
	private String params;
	private Object response;

	public void setServiceMethod(String splitStrs) {
		String[] splitStr = splitStrs.split("\\.");
		this.service = splitStr[0];
		this.method = splitStr[1];
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String toString() {
		return "[service=" + this.service + ",method=" + this.method + ",params=" + this.params + "]";
	}

}
