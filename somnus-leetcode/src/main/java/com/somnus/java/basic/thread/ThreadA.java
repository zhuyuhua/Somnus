package com.somnus.java.basic.thread;

class B {
	public int f(int i) {
		return i + i;
	}

	public int f(int i, int j) {
		return i + j;
	}

	public int f(char c) {
		return c;
	}
}

public class ThreadA extends B {
	@Override
	public int f(int i, int j) {
		return super.f(i, j);
	}

	@Override
	public int f(int j) {
		return -j;
	}

	public static void main(String[] args) {
		B x = new ThreadA();
		System.out.println(x.f(5, 10) + " " + x.f(4) + " " + x.f('A'));
	}
}
