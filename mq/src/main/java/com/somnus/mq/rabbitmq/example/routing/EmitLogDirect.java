package com.somnus.mq.rabbitmq.example.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {

	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		String routingkey = "bb";
		String message = "message";

		channel.basicPublish(EXCHANGE_NAME, routingkey, null,
				message.getBytes());
		System.out.println(" [x] Sent '" + routingkey + "':'" + message + "'");

		channel.close();
		connection.close();
	}

}
