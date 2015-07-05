package com.somnus.core.basic.thread.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static int semaphoreCount = 3;

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(20);
		Semaphore sp = new Semaphore(semaphoreCount);// 创建Semaphore信号量，初始化许可大小为5

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}

			SemaphoreRunnable runnable = new SemaphoreRunnable(sp);
			service.execute(runnable);

		}
		service.shutdown();

	}

}

class SemaphoreRunnable implements Runnable {

	private final Semaphore sp;

	public SemaphoreRunnable(Semaphore sp) {
		this.sp = sp;
	}

	@Override
	public void run() {
		try {
			sp.acquire();// 请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态。
		} catch (InterruptedException e) {
		}
		
		System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有"
				+ (SemaphoreDemo.semaphoreCount - sp.availablePermits())
				+ "个并发.");

		try {
			Thread.sleep(new Random().nextInt(10000));
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		synchronized (this) {
			System.out
					.println("线程" + Thread.currentThread().getName() + "即将离开");
			sp.release();// 释放许可，许可数加1
			// 下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
			System.out.println("线程" + Thread.currentThread().getName()
					+ "已离开，当前已有"
				+ (SemaphoreDemo.semaphoreCount - sp.availablePermits())
					+ "个并发.");
		}
	}
}
