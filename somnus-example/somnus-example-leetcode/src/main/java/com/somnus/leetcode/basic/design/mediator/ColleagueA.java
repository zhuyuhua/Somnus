package com.somnus.leetcode.basic.design.mediator;

public class ColleagueA extends AbstractColleague {

	@Override
	protected void changeNumber(int number, Mediator mediator) {
		setNumber(number);
		mediator.affectColleagueB();
	}

}
