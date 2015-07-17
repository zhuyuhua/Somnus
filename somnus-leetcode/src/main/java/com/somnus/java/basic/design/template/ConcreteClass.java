package com.somnus.java.basic.design.template;

public class ConcreteClass extends AbstractClass{

	@Override
	protected void method1() {
		
		super.method1();
		System.out.println(this+".ConcreteClass.method1");
	}

	@Override
	protected void method2() {
		super.method2();
		System.out.println(this+".ConcreteClass.method2");
	}

}
