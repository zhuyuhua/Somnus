package com.somnus.leetcode.basic.network.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("Server started,waiting for message");
		Socket socket = serverSocket.accept();
		PrintWriter pw = null;
		while (true) {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String content = reader.readLine();

			if ("end".equals(content)) {
				serverSocket.close();
				reader.close();
				if (pw != null) {
					pw.close();
				}
				break;
			}

			System.out.println("From client:" + content);
			System.out.print("Server:");
			pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String output = in.readLine();
			pw.println(output);
			pw.flush();
		}
		System.out.println("Client left!Server closed.");
	}

}
