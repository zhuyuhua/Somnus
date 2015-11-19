package com.somnus.leetcode.basic.design.adapter;

/**
 * 直接继承自Adaptee 采用继承实现
 * 
 * @author joe
 *
 */
public class Adapter extends Adaptee implements Target {

	@Override
	public void request() {
		this.specificRequest();
	}

}
