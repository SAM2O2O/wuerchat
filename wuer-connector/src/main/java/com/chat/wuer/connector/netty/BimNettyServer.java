package com.chat.wuer.connector.netty;

import java.util.concurrent.TimeUnit;

import com.chat.wuer.connector.codec.protocol.BimMessageDecoder;
import com.chat.wuer.connector.codec.protocol.BimMessageEncoder;
import com.chrome.chat.common.command.Command;
import com.chrome.chat.common.executor.AbstracteExecutor;
import com.chrome.chat.common.executor.SimpleExecutor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public abstract class BimNettyServer {

	private AbstracteExecutor<Command> executor;
	private ServerBootstrap bootstrap;
	private EventLoopGroup parentGroup;
	private EventLoopGroup childGroup;

	public BimNettyServer() {
		executor = new SimpleExecutor<Command>();
		parentGroup = new NioEventLoopGroup(10, new PrefixThreadFactory("bim-boss-evenloopgroup"));
		int childThreadNum = Runtime.getRuntime().availableProcessors() + 1;
		childGroup = new NioEventLoopGroup(childThreadNum, new PrefixThreadFactory("bim-worker-evenloopgroup"));
		bootstrap = new ServerBootstrap();
		bootstrap.group(parentGroup, childGroup);
		bootstrap.channel(NioServerSocketChannel.class).localAddress(443);
		bootstrap.option(ChannelOption.SO_BACKLOG, 2000);
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		bootstrap.option(ChannelOption.SO_RCVBUF, 256 * 1024);
		bootstrap.option(ChannelOption.SO_SNDBUF, 256 * 1024);
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		bootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT); // 动态缓冲区
		bootstrap.handler(new LoggingHandler(LogLevel.INFO));
		bootstrap.childHandler(new BimChannelInitializer());
	}

	public void start() {
		try {
			if (bootstrap != null) {
				ChannelFuture channelFuture = bootstrap.bind("172.16.36.10", 8443).sync();
				channelFuture.channel().closeFuture().sync();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				parentGroup.shutdownGracefully();
				childGroup.shutdownGracefully();
				parentGroup.terminationFuture().sync();
				childGroup.terminationFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class BimChannelInitializer extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// SSLEngine sslEngine =
			// NettySocketSslContext.getInstance().getServerContext().createSSLEngine();

			ch.pipeline().addLast(new BimMessageDecoder());
			ch.pipeline().addLast(new BimMessageEncoder());
			ch.pipeline().addLast("timeout", new IdleStateHandler(0, 10, 0, TimeUnit.MICROSECONDS));

			// ch.pipeline().addLast(new SslHandler(sslEngine));

			ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50, TimeUnit.SECONDS));
			ch.pipeline().addLast(new AuthResponseHandler());
			ch.pipeline().addLast("keepAliveHandler", new HeartBeatHandler());
			ch.pipeline().addLast(new BimInboundHandler());
		}

	}

	public abstract void loadExecutor(AbstracteExecutor<Command> executor);
}
