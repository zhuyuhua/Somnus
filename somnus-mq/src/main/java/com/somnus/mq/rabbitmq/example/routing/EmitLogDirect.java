package com.somnus.mq.rabbitmq.example.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.somnus.mq.rabbitmq.example.Constants;

public class EmitLogDirect {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		channel.exchangeDeclare(Constants.EXCHANGE_DIRECT_NAME, "direct");
		String message = getMessage(args);

		for (int i = 0; i < 100; i++) {

			String severity = Constants.serverity_black;
			if (i % 2 == 0) {
				severity = Constants.serverity_orange;
			} else {
				severity = Constants.serverity_black;
			}
			channel.basicPublish(Constants.EXCHANGE_DIRECT_NAME, severity,
					null, message.getBytes());
			System.out
					.println(" [x] Sent '" + severity + "':'" + message + "'");
			Thread.sleep(3000);
		}

		channel.close();
		conn.close();

	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello World!";
		return joinStrings(strings, " ");

	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
