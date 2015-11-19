package com.somnus.leetcode.basic.design.observer;

import java.util.Vector;

public abstract class AbstractSubject {
	
	private final Vector<Observer> obs = new Vector<>();
	
	public synchronized void registerObserver(Observer obs){
		this.obs.add(obs);
	}
	
	public synchronized void unRegisterObserver(Observer obs){
		this.obs.add(obs);
	}
	
	protected void notifyObserver() {
		for(Observer o:obs){
			o.update();
		}
	}
	
	public abstract void doSomething();

}
