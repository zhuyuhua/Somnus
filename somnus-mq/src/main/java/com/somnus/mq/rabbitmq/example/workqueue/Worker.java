package com.somnus.mq.rabbitmq.example.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Worker {

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(NewTask.TASK_QUEUE_NAME, true, false, false, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		channel.basicQos(1);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(NewTask.TASK_QUEUE_NAME, false, consumer);

		while (true) {
			Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("[x] Received '" + message + "'");
			doWork(message);
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}

	}

	private static void doWork(String message) throws InterruptedException {
		for (char ch : message.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}
}
