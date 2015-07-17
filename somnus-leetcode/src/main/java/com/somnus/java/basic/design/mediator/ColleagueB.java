package com.somnus.java.basic.design.mediator;

public class ColleagueB extends AbstractColleague {

	@Override
	protected void changeNumber(int number, Mediator mediator) {
		setNumber(number);
		mediator.affectColleagueA();
	}

}
