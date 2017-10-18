package com.wuerchat.common.command;

import java.util.HashMap;
import java.util.Map;

public class CommandResponse {
	private int version;
	private String action;
	private byte[] params;

	private Map<Integer, String> header = new HashMap<Integer, String>();
	private int errCode;
	private String errInfo;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public byte[] getParams() {
		return params;
	}

	public void setParams(byte[] params) {
		this.params = params;
	}

	public Map<Integer, String> getHeader() {
		return header;
	}

	public void setHeader(Map<Integer, String> header) {
		this.header = header;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public String toString() {
		return "[version=" + this.version + ",action=" + this.action + ",data=" + new String(this.params) + "]";
	}

}
