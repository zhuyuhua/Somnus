package com.somnus.leetcode.basic.design.decorator;

////具体装饰类 蓝牙功能扩展  --> DecoratorBlueTooth  
public class DecoratorBlueTooth extends Decorator {

	public DecoratorBlueTooth(AbstractCellPhone mCellPhone) {
		super(mCellPhone);
	}

	@Override
	public String callNumber() {
		return super.callNumber() + " BlueTooth";
	}

	@Override
	public String sendMessage() {
		return super.sendMessage() + " BlueTooth";
	}

}
