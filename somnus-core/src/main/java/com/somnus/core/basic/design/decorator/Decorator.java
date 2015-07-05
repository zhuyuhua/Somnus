package com.somnus.core.basic.design.decorator;

//抽象接口 --> Decorator
public class Decorator extends AbstractCellPhone {
	protected AbstractCellPhone mCellPhone;

	public Decorator(AbstractCellPhone mCellPhone) {
		this.mCellPhone = mCellPhone;
	}

	@Override
	public String callNumber() {
		return mCellPhone.callNumber();
	}

	@Override
	public String sendMessage() {
		return mCellPhone.sendMessage();
	}

}
