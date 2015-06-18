/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.disruptor.example;

import java.sql.Timestamp;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventHandler;

/**
 * TODO
 * 
 * @author:zhuyuhua
 * @date:2015年3月27日 上午10:07:51
 * @version 0.0.1
 */
public class ValueEventHandler1 implements EventHandler<ValueEvent> {

	private static Logger logger = LoggerFactory
			.getLogger(ValueEventHandler1.class);

	@Override
	public void onEvent(final ValueEvent event, final long sequence,
			final boolean endOfBatch) throws Exception {
		System.out.println("ValueEventHandler1:  Sequence: " + sequence
				+ "  time:" + new Timestamp(System.currentTimeMillis())
				+ "   ValueEvent: " + event.getValue());
		event.setValue("ValueEventHandler1");
		// Thread.sleep(3000);
	}

	public static void main(String[] args) throws InterruptedException {
		int time = new Random().nextInt(2000);
		System.out.println(time);
		Thread.sleep(time);
	}
}
