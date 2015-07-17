package com.somnus.java.basic.design.decorator;

public class IPhoneCellPhone extends AbstractCellPhone {

	@Override
	public String callNumber() {
		return "callNumber from IPhone terminal";
	}

	@Override
	public String sendMessage() {
		return "sendMessage from IPhone terminal";
	}
}
