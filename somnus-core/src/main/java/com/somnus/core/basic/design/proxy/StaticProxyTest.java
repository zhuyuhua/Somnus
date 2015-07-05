package com.somnus.core.basic.design.proxy;

/**
 * 静态代理模式
 * 
 * @author Administrator
 * 
 */
public class StaticProxyTest {

	public static void main(String[] args) {

		Subject subject = new SubjectProxy(new RealSubject());
		subject.request();
	}

}
