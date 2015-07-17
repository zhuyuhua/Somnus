package com.somnus.java.basic.design.command;


/**
 * 1) 命令角色（Command）：声明执行操作的接口。有java接口或者抽象类来实现。
 * 
 * 2) 具体命令角色（Concrete Command）：将一个接收者对象绑定于一个动作；调用接收者相应的操作，以实现命令角色声明的执行操作的接口。
 * 
 * 3) 客户角色（Client）：创建一个具体命令对象（并可以设定它的接收者）。
 * 
 * 4) 请求者角色（Invoker）：调用命令对象执行这个请求。
 * 
 * 5) 接收者角色（Receiver）：知道如何实施与执行一个请求相关的操作。任何类都可能作为一个接收者。
 * 
 * @author Administrator
 * 
 */
public class CommandTest {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();

		Command command = new ConcreteCommand(receiver);

		// 直接调用
		command.execute();

		// 通过调用者来执行
		Invoker invoker = new Invoker();
		invoker.setCommand(command);
		invoker.invoke();
	}
}

class Invoker {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void invoke() {
		this.command.execute();
	}

}

interface Command {
	void execute();
}

class ConcreteCommand implements Command {

	private Receiver receiver;

	public ConcreteCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.doSomething();
	}

}

class Receiver {

	public void doSomething() {
		System.out.println("接受者-业务逻辑处理");
	}

}
