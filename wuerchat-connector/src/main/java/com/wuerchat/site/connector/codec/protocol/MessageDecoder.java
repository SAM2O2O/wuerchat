package com.wuerchat.site.connector.codec.protocol;

import java.util.List;

import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.parser.IProtocolParser;
import com.wuerchat.site.connector.codec.parser.ProtocolParser;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 解码器,使用ReplayingDecoder，每一个deccode必须是
 * 
 * @author Sam
 * @since 2017.09.27
 *
 */
public class MessageDecoder extends ReplayingDecoder<ReplaySignal> {

	private IProtocolParser parser = new ProtocolParser();

	public MessageDecoder() {
		super.state(ReplaySignal.START_POINT);
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//		System.out.println("MessageDecoder::::");
		// receive data from client
		parser.readAndOut(ctx.channel(), in, out, this);

	}

	@Override
	public void checkpoint(ReplaySignal bp) {
		super.checkpoint(bp);
	}

	@Override
	public ReplaySignal state() {
		return super.state();
	}

}
