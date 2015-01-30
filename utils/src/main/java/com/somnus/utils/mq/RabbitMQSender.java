package com.somnus.utils.mq;

import java.io.IOException;

public class RabbitMQSender extends EndPoint implements Runnable {

	private String message;

	public RabbitMQSender(String message) throws IOException {
		super();
		this.message = message;
	}

	@Override
	public void run() {

		try {
			// Thread.sleep(3000);
			System.out.println("send msg:" + message);
			channel.basicPublish("", endPointName, null, message.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
	}

}
