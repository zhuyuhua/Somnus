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
package com.somnus.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.utils.date.DateTimeUtil;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月28日
 */
public class NIOServer {

	private static Logger logger = LoggerFactory.getLogger(NIOServer.class);

	private Selector selector;

	/**
	 * 初始化
	 * 
	 * @param port
	 *            端口
	 * @throws IOException
	 * @since JDK 1.6
	 */
	private void init(int port) throws IOException {
		// 获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();

		// 设置通道为非阻塞
		serverChannel.configureBlocking(false);

		// 将该通道对应的ServerSocket绑定到port端口
		ServerSocket socket = serverChannel.socket();
		socket.bind(new InetSocketAddress(port));

		// 获得一个通道管理器
		selector = Selector.open();

		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 * @since JDK 1.6
	 */
	private void listen() throws Exception {
		logger.debug("服务器启动成功!");
		while (true) {
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			selector.select();
			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Set<SelectionKey> selectKeys = selector.selectedKeys();
			Iterator<SelectionKey> ite = selectKeys.iterator();

			while (ite.hasNext()) {
				SelectionKey key = ite.next();
				// 删除已选的key,以防重复处理
				ite.remove();
				// 客户端请求连接事件
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();
					// 设置成非阻塞
					channel.configureBlocking(false);

					// 在这里可以给客户端发送信息哦
					// channel.write(ByteBuffer.wrap(DateTimeUtil.getFullTodayStr().getBytes("utf-8")));

					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);

					// 获得了可读的事件
				} else if (key.isReadable()) {
					read(key);
				}

			}

		}
	}

	/**
	 * 处理读取客户端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws Exception {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data, "utf-8").trim();
		logger.warn("服务端收到信息：" + msg);

		Thread.sleep(10000);
		msg = DateTimeUtil.getFullTodayStr();
		logger.debug("发给客户端：" + msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes("utf-8"));
		channel.write(outBuffer);// 将消息回送给客户端
	}

	public static void main(String[] args) throws Exception {
		NIOServer server = new NIOServer();
		server.init(8000);
		server.listen();
	}

}
