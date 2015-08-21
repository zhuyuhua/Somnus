package com.somnus.rpc.netty.example.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {

	private final String host;
	private final int port;
	private Selector selector;
	private SocketChannel socketChannel;

	private volatile boolean stop;

	public TimeClientHandler(String host, int port) {
		this.host = host == null ? "127.0.0.1" : host;
		this.port = port;

		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();

			socketChannel.configureBlocking(false);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (Exception e) {
		}

		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> set = selector.selectedKeys();
				Iterator<SelectionKey> it = set.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else {
					System.exit(1);// 失败退出
				}
			}
			if (key.isReadable()) {
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(byteBuffer);
				if (readBytes > 0) {
					byteBuffer.flip();
					byte[] bytes = new byte[byteBuffer.remaining()];
					byteBuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("Now is :" + body);
					this.stop = true;
				} else if (readBytes < 0) {
					key.cancel();
					sc.close();
				} else {
					;
				}
			}
		}
	}

	private void doWrite(SocketChannel sc) throws IOException {
		byte[] req = "QUERY TIME ORDER".getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(req.length);
		buffer.put(req);
		buffer.flip();
		sc.write(buffer);
		if (!buffer.hasRemaining()) {
			System.out.println("Send order 2 server succeed.");
		}
	}

	private void doConnect() throws IOException {
		// 如果直接连接成功，则注册到多路复用器上，发送请求消息，读应答
		if (socketChannel.connect(new InetSocketAddress(host, port))) {
			socketChannel.register(selector, SelectionKey.OP_READ);
		} else {
			//
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
