package com.somnus.core.basic.design.strategy;

public class ConcreteStrategy1 implements IStrategy {

	@Override
	public void doSomething() {
		System.out.println(this);
	}

}
