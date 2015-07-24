package com.somnus.leetcode.basic.thread.threadlocal;

public class Test {

	public static void main(String[] args) {

		ThreadLocalTest test1 = new ThreadLocalTest();
		ThreadLocalTest test2 = new ThreadLocalTest();
		ThreadLocalTest test3 = new ThreadLocalTest();

		test1.start();
		test2.start();
		test3.start();

	}

}
