package com.somnus.leetcode.interview.bank;

public class NumberMachine {

	private NumberMachine() {
	};// 单例模式，只有一个取票机

	private static NumberMachine instance = new NumberMachine();

	public static NumberMachine getInstance() {
		return instance;
	}

	private final NumberManager commonManager = new NumberManager();
	private final NumberManager expressManager = new NumberManager();
	private final NumberManager vipManager = new NumberManager();

	public NumberManager getCommonManager() {
		return commonManager;
	}

	public NumberManager getExpressManager() {
		return expressManager;
	}

	public NumberManager getVipManager() {
		return vipManager;
	}
}
