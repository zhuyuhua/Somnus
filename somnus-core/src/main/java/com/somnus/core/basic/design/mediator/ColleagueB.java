package com.somnus.core.basic.design.mediator;

public class ColleagueB extends AbstractColleague {

	@Override
	protected void changeNumber(int number, Mediator mediator) {
		setNumber(number);
		mediator.affectColleagueA();
	}

}
