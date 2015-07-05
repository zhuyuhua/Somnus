package com.somnus.core.basic.design.adapter;


/**
 * 实现 Target接口 采用对象组合方式实现
 * 
 * @author joe
 *
 */
public class Adapter2 implements Target {

	private final Adaptee adaptee;

	public Adapter2(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();

	}

}
