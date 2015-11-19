package com.somnus.leetcode.basic.design.strategy;

public class Context {
	
	private final IStrategy strategy;
	
	public Context(IStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void execute(){
		strategy.doSomething();
	}

}
