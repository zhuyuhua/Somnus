package com.somnus.rpc.netty.example.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

	public static void main(String[] args) {
		int port = 8080;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			out.println("QUERY TIME ORDER");
			System.out.println("Send order 2 server succeed.");
			String resp = in.readLine();
			System.out.println("Now is :" + resp);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				in = null;
			}

			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e2) {
				}
				socket = null;
			}
		}
	}

}
