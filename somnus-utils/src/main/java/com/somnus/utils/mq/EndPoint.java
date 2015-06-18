package com.somnus.utils.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {

	protected String uri = "localhost";

	protected Channel channel;
	protected Connection connection;
	protected String endPointName = "SimpleQueue";

	public EndPoint() throws IOException {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost(uri);

		connection = factory.newConnection();

		channel = connection.createChannel();

		channel.queueDeclare(endPointName, false, false, false, null);
	}

	/**
	 * 关闭channel和connection。并非必须，因为隐含是自动调用的。
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.channel.close();
		this.connection.close();
	}

}
