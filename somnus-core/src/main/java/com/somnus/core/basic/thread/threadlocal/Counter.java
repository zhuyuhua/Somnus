package com.somnus.core.basic.thread.threadlocal;

public class Counter {

	private static ThreadLocal<Integer> counterContext = new ThreadLocal<Integer>() {

		@Override
		protected synchronized Integer initialValue() {
			return 10;
		}
	};

	public static Integer get() {
		return counterContext.get();
	}

	public static void set(Integer value) {
		counterContext.set(value);
	}

	public static Integer getNextCounter() {
		counterContext.set(counterContext.get() + 1);
		return counterContext.get();
	}

}
