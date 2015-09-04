package com.somnus.rpc.netty.example.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月28日
 */
public class TimeServer {

	public static void main(String[] args) throws Exception {
		int port = 8080;
		new TimeServer().bind(port);
	}

	public void bind(int port) throws Exception {
		// 服务器线程组 用于网络事件的处理 一个用于服务器接收客户端的连接
		// 另一个线程组用于处理SocketChannel的网络读写
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// NIO服务器端的辅助启动类 降低服务器开发难度
			ServerBootstrap boot = new ServerBootstrap();

			boot.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)// 类似NIO中serverSocketChannel
				.option(ChannelOption.SO_BACKLOG, 1024)// 配置TCP参数
				.childHandler(new ChildChannelHandler());// 最后绑定I/O事件的处理类 // 处理网络IO事件

			// 服务器启动后 绑定监听端口 同步等待成功 主要用于异步操作的通知回调 回调处理用的ChildChannelHandler
			ChannelFuture future = boot.bind(port).sync();
			System.out.println("timeServer启动");
			// 等待服务端监听端口关闭
			future.channel().closeFuture().sync();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 优雅退出 释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	/**
	 * 
	 * 网络事件处理器
	 * @author zhuyuhua
	 * @version 0.0.1
	 * @since 2015年8月28日
	 */
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new TimeServerHandler());
		}

	}

}
