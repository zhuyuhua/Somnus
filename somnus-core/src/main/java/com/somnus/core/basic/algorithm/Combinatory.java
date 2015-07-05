package com.somnus.core.basic.algorithm;

import java.util.LinkedList;

/**
 *  用递归实现从M个不同字符中选取N个字符的所有组合
 * 
 * @author Administrator
 * 
 */
public class Combinatory {

	public static void main(String[] args) {
		char[] chs = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();

		int maxCount = 10;
		LinkedList<Character> result = new LinkedList<>();
		// combination(chs, 0, 0, maxCount, "");
		// System.out.println("++++++++++++++++++++++++++++++++++");
		long start = System.currentTimeMillis();
		combination(chs, 0, 0, maxCount, result);
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void combination(char[] chs, int index, int count,
			int maxCount, LinkedList<Character> result) {
		if (count == maxCount) {
			// System.out.println(result);
			return;
		}

		for (int i = index; i < chs.length; i++) {
			result.addLast(chs[i]);
			combination(chs, i + 1, count + 1, maxCount, result);
			result.removeLast();
		}
	}

	public static void combination(char[] chs, int index, int count,
			int maxCount, String result) {
		if (count == maxCount) {
			System.out.println(result);
			return;
		}

		for (int i = index; i < chs.length; ++i) {
			combination(chs, i + 1, count + 1, maxCount, result + chs[i] + " ");
		}
	}

}
