package com.somnus.core.basic.thread.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * CyclicBarrier类似于CountDownLatch也是个计数器，
 * 不同的是CyclicBarrier数的是调用了CyclicBarrier.await()进入等待的线程数，
 * 当线程数达到了CyclicBarrier初始时规定的数目时，所有进入等待状态的线程被唤醒并继续。
 * CyclicBarrier就象它名字的意思一样，可看成是个障碍， 所有的线程必须到齐后才能一起通过这个障碍。
 * CyclicBarrier初始时还可带一个Runnable的参数，
 * 此Runnable任务在CyclicBarrier的数目达到后，所有其它线程被唤醒前被执行。
 * 
 * @author joe
 *
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) throws Exception {

		int barrierCount = 5;
		// 启用线程池
		ExecutorService executor = Executors.newCachedThreadPool();
		CyclicBarrierRunnable barrierAction = new CyclicBarrierRunnable();
		CyclicBarrier barrier = new CyclicBarrier(barrierCount + 1,
				barrierAction);
		System.out.println("参与个数：" + barrier.getParties());

		for (int i = 0; i < barrierCount; i++) {
			executor.execute(new ComponentThread(barrier, "componentName" + i));
		}
		barrier.await();

		executor.shutdown();

	}

}

class ComponentThread implements Runnable {

	private final CyclicBarrier barrier;

	private final String componentName;

	public ComponentThread(CyclicBarrier barrier, String componentName) {
		this.barrier = barrier;
		this.componentName = componentName;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println(componentName + "即将到达集合地点1，目前已有"
					+ barrier.getNumberWaiting() + "个到达.");
			barrier.await();

			Thread.sleep(new Random().nextInt(1000));
			System.out.println(componentName + "即将到达集合地点2，目前已有"
					+ barrier.getNumberWaiting() + "个到达.");
			barrier.await();// 可重用

			Thread.sleep(new Random().nextInt(1000));
			System.out.println(componentName + "即将到达集合地点3，目前已有"
					+ barrier.getNumberWaiting() + "个到达.");
			barrier.await();// 可重用

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

class CyclicBarrierRunnable implements Runnable {

	@Override
	public void run() {
		// 在所有线程都到达Barrier时执行,由租后一个到达的线程执行
		System.out.println("其他线程被唤醒前执行...");
	}

}

