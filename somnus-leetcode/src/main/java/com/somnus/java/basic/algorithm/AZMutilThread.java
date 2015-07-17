package com.somnus.java.basic.algorithm;

/**
 * Java --写两个线程，一个线程打印1-52，另一个线程打印字母A-Z。 打印 顺序为12A34B56C……5152Z
 * 
 * @author Administrator
 * 
 */
public class AZMutilThread {

	public static void main(String[] args) {
		Object obj = new Object();

		Thread1 t1 = new Thread1(obj);
		Thread2 t2 = new Thread2(obj);
		t1.start();
		t2.start();
	}
}

// 一个线程打印1-52
class Thread1 extends Thread {
	private final Object obj;

	public Thread1(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		synchronized (obj) {
			for (int i = 1; i < 53; i++) {
				System.out.print(i + " ");
				if (i % 2 == 0) {
					obj.notifyAll();
					try {
						obj.wait();
					} catch (Exception e) {

					}
				}
			}
		}
	}
}

class Thread2 extends Thread {
	private final Object obj;

	public Thread2(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		synchronized (obj) {
			for (int i = 0; i < 26; i++) {
				System.out.print((char) ('A' + i) + " ");
				obj.notifyAll();
				try {
					if (i != 25) {
						obj.wait();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
