package com.somnus.leetcode.basic.design.proxy;

public class DynamicProxyTest {

	public static void main(String[] args) {

		DynamicProxy proxy = new DynamicProxy();
		Subject subject = (Subject) proxy.bind(new RealSubject());
		subject.request();

	}

}
