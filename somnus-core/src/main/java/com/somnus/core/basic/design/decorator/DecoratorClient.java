package com.somnus.core.basic.design.decorator;

/**
 * 装饰设计模式的结构 Component:定义一个对象接口，可以给这些对象动态的添加职责。
 * ConcreteComponent:定义一个对象可以给这个对象动态的添加职责。
 * Decorator:维护一个指向Component对象的指针，并定义一个与Component接口一致的接口,或者直接实现Component对象。
 * 
 * @author joe
 *
 */
public class DecoratorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AbstractCellPhone mCellPhone = new AndroidCellPhone();
		System.out.println(mCellPhone.callNumber() + "\n"
				+ mCellPhone.sendMessage());
		System.out.println("-------------------------------------------");
		Decorator gps = new DecoratorGPS(mCellPhone);
		System.out.println(gps.callNumber() + "\n" + gps.sendMessage());
		System.out.println("-------------------------------------------");
		Decorator bluetooth = new DecoratorBlueTooth(mCellPhone);
		System.out.println(bluetooth.callNumber() + "\n"
				+ bluetooth.sendMessage());

	}
}

