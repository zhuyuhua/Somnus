package com.somnus.core.basic.design.proxy;

public class SubjectProxy implements Subject {

	private Subject subject;

	public SubjectProxy(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String request() {
		System.out.println(this.getClass().getName());
		System.out.println("before request...");
		String str = subject.request();
		System.out.println("after request...,result=" + str);
		return str;
	}
}
