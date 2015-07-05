package com.somnus.core.basic.thread.concurrent;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {

	public static void main(String[] args) {
		
		// 初始化一个Callable对象和FutureTask对象
		Callable<Integer> pAccount = new CallableAccountImpl();
		FutureTask<Integer> futureTask = new FutureTask<>(pAccount);
		
		// 使用futureTask创建一个线程
		Thread pThread = new Thread(futureTask);
		System.out.println("futureTask线程现在开始启动，启动时间为：" + new Date());
		pThread.start();

		System.out.println("主线程开始执行其他任务");
		// 从其他账户获取总金额
		int totalMoney = new Random().nextInt(10000);
		System.out.println("现在你在其他账户中的总金额为" + totalMoney);
		System.out.println("等待私有账户总金额统计完毕...");

		// 测试后台的计算线程是否完成，如果未完成则等待
		while (!futureTask.isDone()) {
			try {
				Thread.sleep(1000);
				System.out.println("私有账户计算未完成继续等待...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("futureTask线程计算完毕，此时时间为:" + new Date());

		Integer privateAccountMoney = null;
		try {
			privateAccountMoney = futureTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("私有账户金额为" + privateAccountMoney);
		System.out.println("总金额是："
				+ (totalMoney + privateAccountMoney.intValue()));
	}

}

class CallableAccountImpl implements Callable<Integer> {

	private Integer totalMoney;

	@Override
	public Integer call() throws Exception {
		Thread.sleep(10000);
		totalMoney = new Integer(new Random().nextInt(10000));
		System.out.println("你当前有" + totalMoney + "在你的私人账户中");
		return totalMoney;
	}

}