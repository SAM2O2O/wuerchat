package com.wuerchat.connector.codec.protocol;

import com.wuerchat.connector.utils.HexUtils;

/**
 * Decoder之后，乘放置消息内容
 * 
 * @author Sam.TOTO
 * @since 2017.09-27
 *
 */
public class ProtocolPacket {
	private byte[] head;
	private byte[] body;

	public ProtocolPacket() {

	}

	public ProtocolPacket(byte[] head, byte[] body) {
		this.head = head;
		this.body = body;
	}

	public byte[] getHead() {
		return head;
	}

	public void setHead(byte[] head) {
		this.head = head;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public String toString() {
		return "header=" + HexUtils.printHexString(head) + ", body=" + HexUtils.printHexString(body);
	}

}
