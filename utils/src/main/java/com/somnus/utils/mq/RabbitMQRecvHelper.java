package com.somnus.utils.mq;

import java.io.IOException;

public class RabbitMQRecvHelper extends EndPoint {

	public RabbitMQRecvHelper() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static RabbitMQRecvHelper instance() {
		RabbitMQRecvHelper helper = null;
		try {
			helper = new RabbitMQRecvHelper();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return helper;

	}

}
