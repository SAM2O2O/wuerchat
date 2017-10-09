package com.chat.wuer.connector.netty;

import com.chat.wuer.connector.client.ClientChannel;
import com.chat.wuer.connector.codec.parser.ParserConst;
import com.chat.wuer.connector.codec.parser.ProtocolParser;
import com.chat.wuer.connector.codec.protocol.ProtocolPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class BimInboundHandler extends SimpleChannelInboundHandler<ProtocolPacket> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().attr(ParserConst.INPARSER).set(new ProtocolParser());
		ctx.channel().attr(ParserConst.TOCLIENT).set(new ClientChannel(ctx.channel()));
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ProtocolPacket msgBytePacket) throws Exception {

		System.out.println("===============" + msgBytePacket.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

	}

}
