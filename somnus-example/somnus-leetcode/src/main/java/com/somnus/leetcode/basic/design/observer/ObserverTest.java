package com.somnus.leetcode.basic.design.observer;

public class ObserverTest {

	public static void main(String[] args) {
		AbstractSubject subject = new ConcreteSubject();
		subject.registerObserver(new ConcreteObserver1());
		subject.registerObserver(new ConcreteObserver2());
		
		subject.doSomething();
	}

}
