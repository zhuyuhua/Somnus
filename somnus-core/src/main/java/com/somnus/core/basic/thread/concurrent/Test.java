package com.somnus.core.basic.thread.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newCachedThreadPool();
		int count = 10;
		final CyclicBarrier barrier = new CyclicBarrier(count);// 这里有11个barrier
		int[] datas = new int[10204];
		int step = datas.length / count;
		System.out.println("step:" + step);

		for (int i = 0; i < count; i++) {
			int start = i * step;
			int end = (i + 1) * step;
			if (i == count - 1) {
				end = datas.length;
			}
			service.execute(new MyRunnable(barrier, datas, start, end));// 这里一共有10个，然后在MyRunnable会调用await()。
		}
		System.out.println("--");
		barrier.await();// 另外1个在这

		// 合并数据
		service.shutdown();
	}

}

class MyRunnable implements Runnable {

	private final CyclicBarrier barrier;
	private final int[] datas;
	private final int start;
	private final int end;

	public MyRunnable(CyclicBarrier barrier, int[] datas, int start, int end) {
		this.barrier = barrier;
		this.datas = datas;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println(Thread.currentThread().getName() + "即将到达，目前已有"
					+ barrier.getNumberWaiting() + "个到达.");

			barrier.await();

			System.out.println(Thread.currentThread().getName() + "离开");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
