package com.somnus.architecture.spring.integration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String... args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/spring/spring-integration-context.xml");

		// Simple Service
		TempConverter converter = ctx.getBean("simpleGateway",
				TempConverter.class);
		System.out.println(converter.fahrenheitToCelcius(68.0f));

		// Web Service
		converter = ctx.getBean("wsGateway", TempConverter.class);
		System.out.println(converter.fahrenheitToCelcius(68.0f));

	}

}
