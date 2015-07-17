package com.somnus.java.basic.thread.pv;

import java.util.LinkedList;

/**
 * 1、wait()和notify()
 * 
 * @author Administrator
 * 
 */
public class ProducerConsumer1 {

	private final LinkedList<Object> storeHouse = new LinkedList<>();
	private final int MAX = 3;

	public ProducerConsumer1() {

	}

	public static void main(String[] args) {
		ProducerConsumer1 pc = new ProducerConsumer1();
		pc.start();
	}

	public void start() {
		new Producer().start();
		new Consumer().start();
	}

	class Producer extends Thread {

		private final String name = this.getName() + "("
				+ Producer.class.getSimpleName() + ")";

		@Override
		public void run() {
			while (true) {
				synchronized (storeHouse) {
					System.out.println(name + " is running...");
					try {
						while (storeHouse.size() == MAX) {
							System.out.println(name + "storeHouse is full,please wait");
							storeHouse.wait();
							System.out.println(name	+ "storeHouse is full,after wait");
						}
						System.out.println(name	+ "begin to add object to storeHouse");
						Object newObject = new Object();
						if (storeHouse.add(newObject)) {
							System.out.println(name	+ "Producer put a Object to storeHouse.storeHouse'size:"
											+ storeHouse.size());
							Thread.sleep((long) (Math.random() * 100));
							storeHouse.notify();
						}
					} catch (InterruptedException e) {
						System.out.println("producer is interrupted!");
					}
				}
			}
		}
	}

	class Consumer extends Thread {

		private final String name = this.getName() + "("
				+ Consumer.class.getSimpleName() + ")";

		@Override
		public void run() {
			while (true) {
				synchronized (storeHouse) {
					System.out.println(name + " is running...");
					try {
						while (storeHouse.size() == 0) {
							System.out.println(name	+ "storeHouse is empty,please wait.");
							storeHouse.wait();
							System.out.println(name + "storeHouse is empty,after wait.");
						}

						storeHouse.removeLast();
						System.out.println(name
										+ "Comsumer get  a Object from storeHouse,storeHouse'size:"
										+ storeHouse.size());
						Thread.sleep((long) (Math.random() * 1000));
						storeHouse.notify();
					} catch (InterruptedException ie) {
						System.out.println("Consumer is interrupted");
					}
				}
			}
		}
	}
}
