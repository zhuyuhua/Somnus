package com.somnus.rpc.netty.example.aio;

public class TimeServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int port = 8080;

		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		new Thread(timeServer, "NIO-MultilexerTimeServer-001").start();
	}

}
