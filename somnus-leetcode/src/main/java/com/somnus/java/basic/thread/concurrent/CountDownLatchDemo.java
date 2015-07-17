package com.somnus.java.basic.thread.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 模拟项目的开发，只有当每个模块都完成后，项目才完成 每个模块的用时不同
 * 
 * @author joe
 *
 */
public class CountDownLatchDemo {

	private static int SIZE = 30;

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(SIZE);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Random r = new Random();
		Controller controller = new Controller(latch);
		executor.execute(controller);

		for (int i = 0; i < SIZE; i++) {
			executor.execute(new Module("模块" + i, r.nextInt(1000), latch));
		}
		executor.shutdown();
	}

}

class Module implements Runnable {

	private final String moduleName;

	private final int workTime;

	private final CountDownLatch latch;

	public Module(String moduleName, int workTime, CountDownLatch latch) {
		this.moduleName = moduleName;
		this.workTime = workTime;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {  
            work();  
            latch.countDown();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
          
    }  
      
    private void work() throws InterruptedException{  
        TimeUnit.MILLISECONDS.sleep(workTime);  
		System.out.println(moduleName + " 完成，耗时:" + workTime);
	}
}

class Controller implements Runnable {

	private final CountDownLatch latch;

	public Controller(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		try {
			latch.await();
			System.out.println("所有模块都完成，任务完成");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
