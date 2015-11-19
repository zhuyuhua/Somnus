package com.somnus.leetcode.basic.design.template;

public abstract class AbstractClass {
	
	protected void method1() {
		System.out.println(this+".AbstractClass.method1");
	}
	
	protected void method2() {
		System.out.println(this+".AbstractClass.method2");
	}
	
	public final void execute(){
		System.out.println("AbstractClass.execute...");
		method1();
		method2();
	}
	
}
