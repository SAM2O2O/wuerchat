package com.wuerchat.connector.codec.protocol;

import com.wuerchat.connector.codec.parser.IProtocolBuilder;
import com.wuerchat.connector.codec.parser.ProtocolBuilder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 编码器
 * 
 * @author Sam
 * @since 2017.09.27
 * 
 */
public class MessageEncoder extends MessageToByteEncoder<RedisCommand> {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		promise.addListener(new GenericFutureListener<Future<? super Void>>() {

			public void operationComplete(Future<? super Void> future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("write to user success!");
				} else {
					System.out.println("write to user fail");
				}

			}
		});

		super.write(ctx, msg, promise);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, RedisCommand msg, ByteBuf out) throws Exception {

		IProtocolBuilder builder = ProtocolBuilder.getInstance();
		builder.writeAndOut(ctx.channel(), msg, out);

	}

}
