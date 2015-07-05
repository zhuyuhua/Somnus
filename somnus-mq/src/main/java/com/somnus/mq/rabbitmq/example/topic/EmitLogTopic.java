package com.somnus.mq.rabbitmq.example.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.somnus.mq.rabbitmq.example.Constants;

public class EmitLogTopic {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(Constants.EXCHANGE_TOPIC_NAME, "topic");

		args = new String[] { "kern.critical", "kern.aa", "A critical", "111",
				"222" };
		for (int i = 0; i < 100; i++) {

			String message = EmitLogTopic.class.getName()
					+ System.currentTimeMillis();
			channel.basicPublish(Constants.EXCHANGE_TOPIC_NAME, args[i % 2],
					null, message.getBytes());
			System.out.println(" [x] Sent '" + args[i % 2] + "':'" + message
					+ "'");
			Thread.sleep(3000);
		}

		channel.close();
		connection.close();
	}
}
