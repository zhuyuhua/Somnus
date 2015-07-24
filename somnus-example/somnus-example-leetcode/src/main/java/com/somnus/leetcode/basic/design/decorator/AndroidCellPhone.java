package com.somnus.leetcode.basic.design.decorator;

//Android厂商的手机 -->ConcreteComponent
public class AndroidCellPhone extends AbstractCellPhone {

	@Override
	public String callNumber() {
		return "callNumber from Android terminal";
	}

	@Override
	public String sendMessage() {
		return "sendMessage from Android terminal";
	}

}
