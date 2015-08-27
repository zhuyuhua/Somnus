package com.somnus.rpc.netty.example.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * 
 */
public class TimeServer {

	/**
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) throws IOException {
		int port = 8080;

		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:" + port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (server != null) {
				System.out.println("The time server close");
				server.close();
				server = null;
			}
		}

	}
}
