package com.wuerchat.connector.netty;

import com.wuerchat.connector.client.ChannelSession;
import com.wuerchat.connector.codec.parser.ParserConst;
import com.wuerchat.connector.codec.protocol.RedisCommand;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyInboundHandler extends SimpleChannelInboundHandler<RedisCommand> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		/**
		 * 用户建立连接到服务端，执行此方法。
		 */
		ctx.channel().attr(ParserConst.CHANNELSESSION).set(new ChannelSession(ctx.channel()));
		
		
		System.out.println("================NettyInboundHandler.channelActive");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RedisCommand cmd) throws Exception {

		System.out.println("BimInboundHandler===============" + cmd.toString());

		// 转交客户端消息，至客户端

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

	}

}
