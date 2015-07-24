package com.somnus.leetcode.interview.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {

	public static void main(String[] args) {

		// 产生新客户
		generalCustomer();

		// 产生四个普通窗口
		for (int i = 1; i < 5; i++) {
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setWindowId(i);
			commonWindow.start();
		}

		// 产生一个快速窗口
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();

		// 产生一个vip窗口
		ServiceWindow vipWindow = new ServiceWindow();
		vipWindow.setType(CustomerType.VIP);
		vipWindow.start();

	}

	private static void generalCustomer() {
		Executors.newScheduledThreadPool(1)
				.scheduleAtFixedRate(
						new Runnable() { // 匿名内部类
							@Override
							public void run() { // 普通客户
								Integer number = NumberMachine.getInstance()
										.getCommonManager()
										.generateNewManager();
								System.out.println(number + "号普通客户等待服务");

							}
						}, 0, Constants.Common_Customer_Interval_Time,
						TimeUnit.SECONDS);

		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() { // 快速客户
				Integer number = NumberMachine.getInstance()
						.getExpressManager().generateNewManager();
				System.out.println(number + "号快速客户等待服务");
			}
		}, 0, Constants.Common_Customer_Interval_Time * 2, TimeUnit.SECONDS);

		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() { // VIP客户
				Integer number = NumberMachine.getInstance().getVipManager()
						.generateNewManager();
				System.out.println(number + "号VIP客户等待服务");
			}
		}, 0, Constants.Common_Customer_Interval_Time * 6, TimeUnit.SECONDS);

	}

}
