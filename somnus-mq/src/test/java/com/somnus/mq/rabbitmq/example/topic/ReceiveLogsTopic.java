package com.somnus.mq.rabbitmq.example.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.somnus.mq.rabbitmq.example.Constants;

public class ReceiveLogsTopic {

	private final static String[] args = new String[] { "kern.*", "*.cirtical",
			"kern.critical" };

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(Constants.EXCHANGE_TOPIC_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();

		for (String bindingKey : args) {
			channel.queueBind(queueName, Constants.EXCHANGE_TOPIC_NAME,
					bindingKey);
		}

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String routingKey = delivery.getEnvelope().getRoutingKey();

			System.out.println(" [x] Received '" + routingKey + "':'" + message
					+ "'");
			Thread.sleep(1000);
		}
	}
}
