package com.somnus.utils.mq;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class RabbitMQSendHelper {

	public static RabbitMQSendHelper instance() {
		RabbitMQSendHelper helper = null;
		try {
			helper = new RabbitMQSendHelper();
		} catch (Exception e) {

		}
		return helper;

	}

	public void sendMSGtoRabbitMQ(String message) {
		if (StringUtils.isEmpty(message)) {
			return;
		}
		RabbitMQSender sender;
		try {
			sender = new RabbitMQSender(message);
			Thread thread = new Thread(sender);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		while (true) {
			instance().sendMSGtoRabbitMQ("" + new Date());
			Thread.sleep(3000);
		}

	}

}
