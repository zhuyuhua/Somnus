package com.somnus.core.basic.thread.pv;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock方式
 * 
 * @author Administrator
 * 
 */
public class ProducerConsumer2 {

	private final LinkedList<Object> myList = new LinkedList<>();
	private final int MAX = 5;

	private final Lock lock = new ReentrantLock();
	private final Condition full = lock.newCondition();
	private final Condition empty = lock.newCondition();

	public ProducerConsumer2() {
	}

	public void start() {
		new Producer().start();
		new Consumer().start();
	}

	public static void main(String[] args) {
		ProducerConsumer2 s2 = new ProducerConsumer2();
		s2.start();
	}

	class Producer extends Thread {
		@Override
		public void run() {
			while (true) {
				lock.lock();
				try {
					while (myList.size() == MAX) {
						System.out.println("warning:it's full!");
						full.await();
					}
					Object obj = new Object();
					if (myList.add(obj)) {
						System.out.println("Producer:" + obj);
						Thread.sleep((long) (Math.random() * 2000));
						empty.signalAll();
					}
				} catch (InterruptedException e) {
					System.out.println("producer is interrupted!");
				} finally {
					lock.unlock();
				}
			}
		}
	}

	class Consumer extends Thread {
		@Override
		public void run() {
			while (true) {
				lock.lock();
				try {
					while (myList.size() == 0) {
						System.out.println("warning:it's empty!");
						empty.await();
					}

					Object o = myList.removeLast();
					System.out.println("Consumer:" + o);
					Thread.sleep((long) (Math.random() * 2000));
					full.signalAll();

				} catch (InterruptedException e) {
					System.out.println("consumer is interrupted!");
				} finally {
					lock.unlock();
				}
			}
		}
	}

}
