package com.somnus.core.basic.design.memento;


/**
 * 发起人：记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。
 * 备忘录：负责存储发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
 * 负责人：对备忘录进行管理，保存和提供备忘录，不能对备忘录的内容进行操作或检查。
 * 
 * @author joe
 *
 */
public class MementoClient {

	public static void main(String[] args) {
		Originator o = new Originator();
		Caretaker c = new Caretaker();

		// 改变负责人对象的状态
		o.setState("On");

		// 创建备忘录对戏那个，并将发起人对象的状态存储起来
		c.saveMemento(o.createMemento());

		// 修改发起人的状态
		o.setState("Off");
		o.restoreMemento(c.retrieveMemento());
		System.out.println(o.getState());

	}
}
