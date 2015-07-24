package com.somnus.example.spring.threadlocal;

import java.util.ArrayList;
import java.util.List;

public class TestThreadLocal {

	public static void main(String[] args) {
		new Thread(new TestRunnable("name1", 1000L, true)).start();
		new Thread(new TestRunnable("name2", 700L, true)).start();
		new Thread(new TestRunnable("name3", 888, true)).start();
	}
}

// 简单记录用户是否可以访问，可以用于全局权限控制等
class User {
	private final String name;
	private final boolean isAllow;

	public User(String name, boolean isAllow) {
		this.name = name;
		this.isAllow = isAllow;
	}

	public String getName() {
		return name;
	}

	public boolean isAllow() {
		return isAllow;
	}

	@Override
	public String toString() {
		return "用户名:" + name + "\t是否允许访问:" + isAllow;
	}
}

// 用于记录每一步骤耗时…,可以用于每一步的性能分析
class TimeConsumer {
	// 名称
	private final String name;
	// 耗时数据列表
	private final List<Long> steps;

	public TimeConsumer(String name, long start) {
		this.name = name;
		steps = new ArrayList<Long>();
		steps.add(start);
	}

	public void andStep(long step) {
		steps.add(step);
	}

	@Override
	public String toString() {
		StringBuffer br = new StringBuffer("操作[" + name + "]共有"
				+ (steps.size() - 1) + "步\n");
		for (int i = 1; i < steps.size(); i++) {
			br.append("\t|--耗时[" + (steps.get(i) - steps.get(0)) + "ms]\n");
		}
		br.append("\n");
		return br.toString();
	}
}

// threadlocal 管理类
class ThreadLocalManager {
	// 用于全局记录user访问权限
	private final ThreadLocal<User> userLocal;
	// 用于全局记录用户每一步的耗时
	private final ThreadLocal<TimeConsumer> timeLocal;
	private static ThreadLocalManager local = new ThreadLocalManager();

	private ThreadLocalManager() {
		userLocal = new ThreadLocal<User>();
		timeLocal = new ThreadLocal<TimeConsumer>();
	}

	public static ThreadLocalManager getInstanse() {
		return local;
	}

	public void addUser(User user) {
		userLocal.set(user);
	}

	public User getUser() {
		return userLocal.get();
	}

	public void addTime(TimeConsumer timeConsumer) {
		timeLocal.set(timeConsumer);
	}

	public void addTime(long l) {
		TimeConsumer time = timeLocal.get();
		timeLocal.remove();
		time.andStep(l);
		timeLocal.set(time);
	}

	public TimeConsumer getTime() {
		return timeLocal.get();
	}
}

// 用于测试，多线程实现
class TestRunnable implements Runnable {
	String name;
	long l;
	boolean isAllow;

	TestRunnable(String name, long l, boolean isAllow) {
		this.name = name;
		this.l = l;
		this.isAllow = isAllow;
	}

	@Override
	public void run() {
		ThreadLocalManager local = ThreadLocalManager.getInstanse();
		local.addUser(new User(name, isAllow));
		local.addTime(new TimeConsumer(name, System.currentTimeMillis()));
		// 做某个业务，并记录时间
		doThings(l);
		local.addTime(System.currentTimeMillis());
		// 做某个业务，并记录时间
		doThings(l);
		local.addTime(System.currentTimeMillis());
		// 业务做完，打印日志
		System.out.println(local.getUser());
		System.out.println(local.getTime());
	}

	// 模拟具体业务的处理步骤
	private void doThings(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
