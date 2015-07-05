package com.somnus.core.basic.design.mediator;

public class Mediator {

	private final ColleagueA colleagueA;
	private final ColleagueB colleagueB;
	
	public Mediator(ColleagueA colleagueA,ColleagueB colleagueB) {
		this.colleagueA = colleagueA;
		this.colleagueB = colleagueB;
	}
	
	public void affectColleagueB() {
		int number = colleagueA.getNumber();
		colleagueB.setNumber(number*100);
	}

	public void affectColleagueA() {
		int number = colleagueB.getNumber();
		colleagueA.setNumber(number*10);
	}

}
