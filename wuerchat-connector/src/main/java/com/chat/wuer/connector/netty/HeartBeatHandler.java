package com.chat.wuer.connector.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * 负责客户端发送的业务心跳
 * 
 * @author Sam
 * @since 2017.09.28
 *
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

	private volatile ScheduledFuture<?> keepAliveResult;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ctx

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (keepAliveResult != null) {
			boolean result = keepAliveResult.cancel(true);
			System.out.println(result + "=" + keepAliveResult.cause());
		}
		ctx.fireExceptionCaught(cause);
	}

	private Object keepAliveAck() {
		return null;
	}
}
