package com.wuerchat.common.command;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理完成后的消息载体
 * 
 * @author Sam
 * @since 2017-09.30
 */
public class Command {
	private String service;
	private String method;
	private byte[] params;
	private Object response;

	private Map<String, Object> fields = new HashMap<String, Object>();

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

	public byte[] getParams() {
		return params;
	}

	public void setParams(byte[] params) {
		this.params = params;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public <T> T getField(String k, Class<T> t) {
		Object obj = fields.get(k);
		return obj == null ? null : (T) obj;
	}

	public Command setFields(Map<String, Object> map) {
		this.fields.putAll(map);
		return this;
	}

	public Command setField(String k, Object v) {
		this.fields.put(k, v);
		return this;
	}

	public String toString() {
		return "[service=" + this.service + ",method=" + this.method + ",params=" + this.params + "]";
	}

}
