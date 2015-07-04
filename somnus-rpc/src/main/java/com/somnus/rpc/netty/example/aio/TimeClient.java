package com.somnus.rpc.netty.example.aio;

public class TimeClient {

	public static void main(String[] args) {
		int port = 8080;
		TimeClientHandler handler = new TimeClientHandler("127.0.0.1", port);
		new Thread(handler, "TimeClient-001").start();
	}

}
