package com.somnus.core.basic.thread.threadlocal;

public class ThreadLocalTest extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {

			System.out.println("Thread[" + Thread.currentThread().getName()
					+ "],counter=" + Counter.getNextCounter());
		}
	}

}
