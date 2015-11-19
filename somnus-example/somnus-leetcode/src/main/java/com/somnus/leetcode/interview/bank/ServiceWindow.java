package com.somnus.leetcode.interview.bank;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow {

	private CustomerType type = CustomerType.COMMON;// 客户类型

	private int windowId = 1;// 窗口号码

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	// 线程池执行任务
	public void start() {
		Executors.newSingleThreadExecutor().execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					switch (type) {
					case COMMON:
						commonService();
						break;
					case EXPRESS:
						expressService();
						break;
					case VIP:
						vipService();
						break;
					default:
						break;
					}
				}
			}

		});
	}

	private void commonService() {
		String windowName = windowId + "号" + type + "窗口";
		System.out.println(windowName + "正在获取任务...");

		// 获取马上要为之服务的号码
		Integer number = NumberMachine.getInstance().getCommonManager()
				.fetchServiceNumber();
		if (number != null) {
			int maxRandom = Constants.Max_Service_Time
					- Constants.Min_Service_Time;
			long serverTime = new Random().nextInt(maxRandom) + 1
					+ Constants.Min_Service_Time;

			try {
				Thread.sleep(serverTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(windowName + "完成为第" + number + "个普通客户服务，耗时:"
					+ serverTime + " Millis");

		} else {
			System.out.println(windowName + "没有任务...休息1秒钟...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void expressService() {
		String windowName = windowId + "号" + type + "窗口";
		System.out.println(windowName + "正在获取任务...");
		Integer number = NumberMachine.getInstance().getExpressManager()
				.fetchServiceNumber();
		if (number != null) {
			int serviceTime = Constants.Min_Service_Time;
			try {
				Thread.sleep(serviceTime); // 快速客户服务时间，最小值
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(windowName + "完成为第" + number + "个" + type
					+ "客户服务；耗时: " + serviceTime + " Millis");
		} else {
			System.out.println(windowName + "没有快速任务...为普通客户服务...");
			commonService(); // 为普通客户服务

		}
	}

	private void vipService() {
		String windowName = windowId + "号" + type + "窗口";
		System.out.println(windowName + "正在获取任务...");
		Integer number = NumberMachine.getInstance().getVipManager()
				.fetchServiceNumber();
		if (number != null) {
			int maxRandom = Constants.Max_Service_Time
					- Constants.Min_Service_Time;
			long serverTime = new Random().nextInt(maxRandom) + 1
					+ Constants.Min_Service_Time;
			try {
				Thread.sleep(serverTime); // 服务时间
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(windowName + "完成为第" + number + "个" + type
					+ "客户服务；耗时: " + serverTime + " Millis");
		} else {
			System.out.println(windowName + "没有VIP任务...为普通客户服务...");
			commonService(); // 为普通客户服务

		}
	}

}
