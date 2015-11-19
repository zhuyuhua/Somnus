package com.somnus.leetcode.basic.design.memento;

/**
 * 发起人角色类，发起人角色利用一个新创建的备忘录对象将自己的内部状态存储起来。
 * 
 * @author joe
 *
 */
public class Originator {

	private String state;

	// 工厂方法，返回一个新的备忘录对象
	public Memento createMemento() {
		return new Memento(state);
	}

	// 将发起人恢复到备忘录对象所记载的状态
	public void restoreMemento(Memento memento) {
		this.state = memento.getState();
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
		System.out.println("当前的状态：" + this.state);
	}

}
