package com.somnus.java.basic.design.observer;

public class ConcreteObserver1 implements Observer {

	@Override
	public void update() {
		System.out.println(this);
	}

}
