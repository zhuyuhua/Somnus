/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.rpc.netty.example.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 客户端
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月28日
 */
public class Client {

	/**
	 * 链接服务器
	 * 
	 * @param port
	 * @param host
	 * @throws Exception
	 */
	public void connect(int port, String host) throws Exception {
		// 网络事件处理线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			// 配置客户端启动类
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					// 设置封包 使用一次大数据的写操作，而不是多次小数据的写操作
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new FixedLengthFrameDecoder(30));// 设置定长解码器
							ch.pipeline().addLast(new StringDecoder());// 设置字符串解码器
							ch.pipeline().addLast(new ClientHandler());// 设置客户端网络IO处理器
						}
					});
			// 连接服务器 同步等待成功
			ChannelFuture f = b.connect(host, port).sync();
			// 同步等待客户端通道关闭
			f.channel().closeFuture().sync();
		} finally {
			// 释放线程组资源
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8000;
		new Client().connect(port, "127.0.0.1");

	}
}
