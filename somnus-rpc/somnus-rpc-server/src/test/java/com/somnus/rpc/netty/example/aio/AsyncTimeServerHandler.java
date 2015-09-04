package com.somnus.rpc.netty.example.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeServerHandler implements Runnable {

	private final int port;

	CountDownLatch latch;

	AsynchronousServerSocketChannel asynchronousServerSocketChannel;

	public AsyncTimeServerHandler(int port) {
		this.port = port;
		try {

			asynchronousServerSocketChannel = AsynchronousServerSocketChannel
					.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
			System.out.println("The time server is start in port:" + port);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {

		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	private void doAccept() {
		// asynchronousServerSocketChannel.accept(this, handler).accept(this,
		// new AcceptCompletionHandler());

	}

}
