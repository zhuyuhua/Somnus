package com.somnus.mq.rabbitmq.example.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.somnus.mq.rabbitmq.example.Constants;

public class ReceiveLogsDirect {

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(Constants.EXCHANGE_DIRECT_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();

		// if (argv.length < 1) {
		// System.err
		// .println("Usage: ReceiveLogsDirect [info] [warning] [error]");
		// System.exit(1);
		// }

		// for (String severity : argv) {
		channel.queueBind(queueName, Constants.EXCHANGE_DIRECT_NAME,
				Constants.serverity_orange);
		// }

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String routingKey = delivery.getEnvelope().getRoutingKey();

			System.out.println(" [x] Received '" + routingKey + "':'" + message
					+ "'");
		}
	}
}
