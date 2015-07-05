package com.somnus.core.basic.design.memento;

/**
 * 负责人角色类，负责人角色负责保存备忘录对象，但是从不修改（甚至不查看）备忘录对象的内容。
 * 
 * @author joe
 *
 */
public class Caretaker {

	private Memento memento;

	public Memento retrieveMemento() {
		return this.memento;
	}

	public void saveMemento(Memento memento) {
		this.memento = memento;
	}
}
