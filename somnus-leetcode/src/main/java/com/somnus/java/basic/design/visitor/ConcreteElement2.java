package com.somnus.java.basic.design.visitor;

public class ConcreteElement2 implements Element {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void doSomething() {
		System.out.println("这是元素2");
	}

}
