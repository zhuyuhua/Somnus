package com.somnus.leetcode.basic.network.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientDemo {

	public static void main(String[] args) throws Exception {
		Socket client = new Socket("127.0.0.1", 9999);


		PrintWriter pw = null;
		InputStreamReader reader = null;

		System.out.println("Client started,read to write content.");
		String input = null;
		while (true) {
			System.out.print("Client:");
			input = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
			pw = new PrintWriter(client.getOutputStream());
			pw.println(input);
			pw.flush();
			if (input.equals("end")) {
				client.close();
				pw.close();
				if (reader != null) {
					reader.close();
				}
				break;
			}

			reader = new InputStreamReader(client.getInputStream());
			System.out.println("From Server:"
					+ new BufferedReader(reader).readLine());

		}

	}

}
