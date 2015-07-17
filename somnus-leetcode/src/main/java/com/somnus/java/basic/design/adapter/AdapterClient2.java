package com.somnus.java.basic.design.adapter;

public class AdapterClient2 {
	public static void main(String[] args) {
		Adaptee adaptee = new Adaptee();
		Target target = new Adapter2(adaptee);
		target.request();
	}

}
