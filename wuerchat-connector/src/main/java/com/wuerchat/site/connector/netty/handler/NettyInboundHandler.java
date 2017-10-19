package com.wuerchat.site.connector.netty.handler;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.executor.AbstracteExecutor;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.parser.ParserConst;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyInboundHandler extends SimpleChannelInboundHandler<RedisCommand> {
	private AbstracteExecutor<Command> executor;

	public NettyInboundHandler(AbstracteExecutor<Command> executor) {
		this.executor = executor;
	}

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
		// ChannelSession channelSession =
		// ctx.channel().attr(ParserConst.CHANNELSESSION).get();
		// ChannelManager.getInstance().delChannel(channelSession.getUserId());
		// System.out.println("channel manager size=" +
		// ChannelManager.getInstance().getChannelSet().size());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RedisCommand cmd) throws Exception {

		ChannelSession channelSession = ctx.channel().attr(ParserConst.CHANNELSESSION).get();

		// String userid = cmd.getUserId();
		// System.out.println("userId=" + userid);
		// channelSession.setUserId(userid);
		// ChannelManager.getInstance().addChannel(channelSession.getUserId(),
		// channelSession);
		// // 转交客户端消息，至客户端
		// ChannelManager.getInstance().getChannelSession(channelSession.getUserId()).send(null);

		Command command = new Command();
		command.setServiceMethod(cmd.getParameterByIndex(1));
		command.setParams(cmd.getParameterByIndex(2));
		command.setField("channel_session", channelSession);

		this.executor.execute(command.getService(), command);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		System.out.println("channel exeception happen" + cause.getMessage());
		ctx.channel().close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

	}

}
