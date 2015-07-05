package com.somnus.core.basic.design.proxy;

public class RealSubject implements Subject {

	@Override
	public String request() {
		System.out.println(this);
		return this + " return.";
	}

}
