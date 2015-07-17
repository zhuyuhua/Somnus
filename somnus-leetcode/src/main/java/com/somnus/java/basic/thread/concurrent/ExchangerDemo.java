package com.somnus.java.basic.thread.concurrent;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		final Exchanger<String> exchanger = new Exchanger<>();
		service.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(new Random().nextInt(1000));
					String data1 = "aaa";
					System.out.println("线程" + Thread.currentThread().getName()
							+ " 正在把数据:" + data1 + "换出去");
					String data2 = exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据是：" + data2);
				} catch (Exception e) {

				}
			}
		});

		service.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(new Random().nextInt(1000));
					String data1 = "bbb";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "换出去");
					String data2 = exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据为" + data2);

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		service.shutdown();
	}

}
