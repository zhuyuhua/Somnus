package com.somnus.leetcode.basic.design.singleton;

public class Singleton {

	public static void main(String[] args) {
		Singleton instance = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		System.out.println(instance == instance2);
	}

	private Singleton() {
	}

	private static class SingletonHolder {
		private static Singleton instance = new Singleton();
	}

	private static Singleton getInstance() {
		return SingletonHolder.instance;
	}

}
