package com.wuerchat.connector.netty;

import java.util.Random;

import com.wuerchat.connector.client.ChannelManager;
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
		System.out.println("================NettyInboundHandler.channelInactive");
		ChannelSession channelSession = ctx.channel().attr(ParserConst.CHANNELSESSION).get();
		ChannelManager.getInstance().delChannel(channelSession.getUserId());
		System.out.println("del channel manager userid=" + channelSession.getUserId());
		System.out.println("channel manager size=" + ChannelManager.getInstance().getChannelSet().size());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RedisCommand cmd) throws Exception {

		System.out.println("BimInboundHandler===============" + cmd.toString());

		ChannelSession channelSession = ctx.channel().attr(ParserConst.CHANNELSESSION).get();

		String userid = cmd.getUserId();
		System.out.println("userId=" + userid);
		channelSession.setUserId(userid);
		ChannelManager.getInstance().addChannel(channelSession.getUserId(), channelSession);
		// 转交客户端消息，至客户端
		ChannelManager.getInstance().getChannelSession(channelSession.getUserId()).send(null);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

	}

}
