package com.somnus.java.basic.algorithm;

/**
 * 从n个数里取出m个数的组合是n*(n-1)*...*(n-m+1)/m*(m-1)*...2*1
 */

public class Combinatory3 {

	private static char[] a = { 'a', 'b', 'c' };

	public static void main(String[] args) {
		select(2);
	}

	private static void select(int k) {
		char[] result = new char[k];
		subselect(0, 1, result, k);

	}

	private static void subselect(int head, int index, char[] r, int k) {
		for (int i = head; i < a.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = a[i];
				System.out.println("i=" + (i) + ";index=" + (index));
				subselect(i + 1, index + 1, r, k);
			} else if (index == k) {
				r[index - 1] = a[i];
				System.out.print(";i=" + (i) + ";index=" + (index)
						+ ";index==k:" + (index == k));
				System.out.print(i + "===");
				System.out.println(r);
				subselect(i + 1, index + 1, r, k);
			} else {
				System.out.println("++");
				return;// 返回到何处？奇怪
			}

		}
	}

}
