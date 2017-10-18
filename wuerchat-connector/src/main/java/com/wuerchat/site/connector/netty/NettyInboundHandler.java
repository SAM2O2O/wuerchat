package com.wuerchat.site.connector.netty;

import java.util.HashMap;

import com.google.protobuf.ByteString;
import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.wuerchat.site.business.service.BusinessService;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.parser.ParserConst;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.wuerchat.site.protobuf.core.TransportPackage;

import io.netty.channel.ChannelFuture;
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
		// ChannelSession channelSession =
		// ctx.channel().attr(ParserConst.CHANNELSESSION).get();
		// ChannelManager.getInstance().delChannel(channelSession.getUserId());
		// System.out.println("channel manager size=" +
		// ChannelManager.getInstance().getChannelSet().size());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RedisCommand cmd) throws Exception {

		System.out.println("BimInboundHandler===============" + cmd.toString());

		// ChannelSession channelSession =
		// ctx.channel().attr(ParserConst.CHANNELSESSION).get();

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

		if (command.getService().contains("msg")) {

		} else if (command.getService().startsWith("Api")) {
			System.out.println("execute api request from client");
			// invoke interface of business
			CommandResponse rs = (CommandResponse) new BusinessService().process(command);

			TransportPackage.PackageData data = TransportPackage.PackageData.newBuilder()
					.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(rs.getParams())).build();

			System.out.println("rs=" + rs.toString());

			// return ret
			ChannelFuture writeFuture = ctx.channel().writeAndFlush(new RedisCommand().add(1).add(rs.getAction()).add(data.toByteArray()));
			if (writeFuture.isSuccess()) {
				System.out.println("write data sucess");
			} else {
				System.out.println("write data error");
			}
			ctx.channel().close();
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

	}

}
