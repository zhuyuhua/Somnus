package com.somnus.leetcode.basic.design.mediator;

public abstract class AbstractColleague {
	
	private int number;
	
	protected abstract void changeNumber(int number,Mediator mediator);

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
