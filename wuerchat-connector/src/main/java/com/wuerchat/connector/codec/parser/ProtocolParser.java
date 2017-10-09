package com.wuerchat.connector.codec.parser;

import java.util.List;

import com.wuerchat.connector.codec.protocol.MessageDecoder;
import com.wuerchat.connector.codec.protocol.ProtocolPacket;
import com.wuerchat.connector.codec.protocol.ReplaySignal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * 
 * @author ay.sam
 * @since 2017-09-27
 * 
 */
public class ProtocolParser implements IProtocolParser {

	private int HEAD_LENGTH = 8;
	private int BODY_LENGTH = 0;
	private byte[] headBuffer;
	private byte[] bodyBuffer;

	// private ProtocolParser() {
	// }
	//
	// public static IProtocolParser getInstance() {
	// return ISingletonHolder.instance;
	// }
	//
	// public interface ISingletonHolder {
	// IProtocolParser instance = new ProtocolParser();
	// }

	public void readAndOut(Channel ch, ByteBuf inByte, List<Object> out, MessageDecoder decoder) {
		switch (decoder.state()) {
		case START_POINT:
			// if (inByte.readByte() == 1) {
			// }
			byte[] readByte = new byte[5];
			inByte.readBytes(readByte);
			System.out.println(new String(readByte));
			decoder.checkpoint(ReplaySignal.START_POINT);
			break;

		case HEADER_POINT:
			byte type = inByte.getByte(inByte.readerIndex());
			if (type > 1) {
				headBuffer = new byte[HEAD_LENGTH];
				inByte.readBytes(headBuffer);
				decoder.checkpoint(ReplaySignal.BODY_POINT);
				BODY_LENGTH = (0x000000ff & headBuffer[2]) + ((0x000000ff & headBuffer[1]) << 8);
			} else {
				inByte.readByte();
				decoder.checkpoint(ReplaySignal.START_POINT);
				buildProtocolPacket(out);
			}
			break;

		case BODY_POINT:
			bodyBuffer = new byte[BODY_LENGTH];
			inByte.readBytes(bodyBuffer);
			
			// #TODO 对body进行解密
			
			decoder.checkpoint(ReplaySignal.START_POINT);
			buildProtocolPacket(out);
			break;

		default:
			System.out.print("error......");
			break;
		}
	}

	private void buildProtocolPacket(List<Object> out) {
		out.add(new ProtocolPacket(headBuffer, bodyBuffer));
		this.headBuffer = new byte[0];
		this.bodyBuffer = new byte[0];
	}

}
