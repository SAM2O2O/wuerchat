package com.wuerchat.site.connector.netty;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.executor.AbstracteExecutor;
import com.wuerchat.common.executor.SimpleExecutor;
import com.wuerchat.site.connector.codec.protocol.MessageDecoder;
import com.wuerchat.site.connector.codec.protocol.MessageEncoder;
import com.wuerchat.site.connector.netty.handler.NettyInboundHandler;

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

public abstract class NettyServer {

	private AbstracteExecutor<Command> executor;
	private ServerBootstrap bootstrap;
	private EventLoopGroup parentGroup;
	private EventLoopGroup childGroup;

	public NettyServer() {
		parentGroup = new NioEventLoopGroup(10, new PrefixThreadFactory("bim-boss-evenloopgroup"));
		int childThreadNum = Runtime.getRuntime().availableProcessors() + 1;
		childGroup = new NioEventLoopGroup(childThreadNum, new PrefixThreadFactory("bim-worker-evenloopgroup"));
		bootstrap = new ServerBootstrap();
		bootstrap.group(parentGroup, childGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.SO_BACKLOG, 2000);
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		bootstrap.option(ChannelOption.SO_RCVBUF, 256 * 1024);
		bootstrap.option(ChannelOption.SO_SNDBUF, 256 * 1024);
		bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		bootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT); // 动态缓冲区
		bootstrap.handler(new LoggingHandler(LogLevel.ERROR));
		bootstrap.childHandler(new BimChannelInitializer());

		executor = new SimpleExecutor<Command>();
		loadExecutor(executor);
	}

	public void start(String address, int port) {
		try {
			if (bootstrap != null) {
				ChannelFuture channelFuture = bootstrap.bind(address, port).sync();
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

			ch.pipeline().addLast(new MessageDecoder());
			ch.pipeline().addLast(new MessageEncoder());
			// ch.pipeline().addLast("timeout", new IdleStateHandler(0, 10, 0,
			// TimeUnit.MICROSECONDS));

			// ch.pipeline().addLast(new SslHandler(sslEngine));

			// ch.pipeline().addLast("readTimeoutHandler", new
			// ReadTimeoutHandler(50, TimeUnit.SECONDS));
			// ch.pipeline().addLast(new AuthResponseHandler());
			// ch.pipeline().addLast("keepAliveHandler", new
			// HeartBeatHandler());
			ch.pipeline().addLast(new NettyInboundHandler(executor));
		}

	}

	public abstract void loadExecutor(AbstracteExecutor<Command> executor);
}
