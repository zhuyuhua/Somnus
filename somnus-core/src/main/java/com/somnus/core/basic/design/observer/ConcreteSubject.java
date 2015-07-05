package com.somnus.core.basic.design.observer;

public class ConcreteSubject extends AbstractSubject {

	@Override
	public void doSomething() {
		System.out.println(this+".doSomething");
		notifyObserver();
	}

}
