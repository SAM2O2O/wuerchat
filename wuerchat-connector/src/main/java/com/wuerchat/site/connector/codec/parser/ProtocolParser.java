package com.wuerchat.site.connector.codec.parser;

import java.util.ArrayList;
import java.util.List;

import com.wuerchat.site.connector.codec.protocol.MessageDecoder;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.wuerchat.site.connector.codec.protocol.ReplaySignal;
import com.wuerchat.site.connector.codec.redis.AbstractParameter;
import com.wuerchat.site.connector.codec.redis.RedisStringParameter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * 
 * @author ay.sam
 * @since 2017-09-27
 * 
 */
public class ProtocolParser implements IProtocolParser {

	public void readAndOut(Channel ch, ByteBuf inByte, List<Object> out, MessageDecoder decoder) {
		switch (decoder.state()) {
		case START_POINT:
			List<AbstractParameter> paramsList = null;

			if (inByte.readByte() == '*') {
				List<Byte> sizeBytes = new ArrayList<Byte>();
				while (true) {
					byte curent = inByte.readByte();
					if (curent == '\r' && inByte.readByte() == '\n') {
						break;
					}
					sizeBytes.add(curent);
				}

				byte[] tempBytes = new byte[sizeBytes.size()];
				for (int i = 0; i < sizeBytes.size(); i++) {
					tempBytes[i] = sizeBytes.get(i);
				}

				System.out.println("start read data size=" + Integer.parseInt(new String(tempBytes)));
				paramsList = new ArrayList<AbstractParameter>(Integer.parseInt(new String(tempBytes)));

				while (true) {
					if (inByte.readByte() == '$') {
						System.out.println("start read String ");
						List<Byte> interBytes = new ArrayList<Byte>();
						while (true) {
							byte curent = inByte.readByte();
							if (curent == '\r' && inByte.readByte() == '\n') {
								break;
							}
							interBytes.add(curent);

						}

						byte[] tempInnerByte = new byte[interBytes.size()];
						for (int j = 0; j < interBytes.size(); j++) {
							tempInnerByte[j] = interBytes.get(j);
						}

						int readByteSize = Integer.parseInt(new String(tempInnerByte));
						System.out.println("String length=" + readByteSize);

						byte[] dataBuffer = new byte[readByteSize];
						inByte.readBytes(dataBuffer);
						String str = new String(dataBuffer);
						System.out.println("read String==" + str);
						paramsList.add(new RedisStringParameter(str));

						if (inByte.readByte() == '\r' && inByte.readByte() == '\n') {
							System.out.println("success!...");
						} else {
							System.out.println("error!.....");
						}

						if (paramsList.size() == Integer.parseInt(new String(tempBytes))) {
							System.out.println("read data finished");
							break;
						}

					}
				}
			}

			decoder.checkpoint(ReplaySignal.START_POINT);

			out.add(buildRedisCommand(paramsList));
			break;

		default:
			System.out.print("error......");
			break;
		}
	}

	private RedisCommand buildRedisCommand(List<AbstractParameter> redisParamsList) {

		return new RedisCommand().addAll(redisParamsList);
	}

}
