package com.somnus.leetcode.basic.design.chain;

/**
 * 责任连模式的结构 责任连模式的类图非常简单，它由一个抽象地处理类和它的一组实现类组成：
 * 抽象处理类：抽象处理类中主要包含一个指向下一处理类的成员变量nextHandler和一个处理请求的方法handRequest，
 * handRequest方法的主要主要思想是，如果满足处理的条件，则有本处理类来进行处理，否则由nextHandler来处理。
 * 
 * 具体处理类：具体处理类主要是对具体的处理逻辑和处理的适用条件进行实现。
 * 
 * @author joe
 *
 */
public class ChainOfResponsibilityClient {

	public static void main(String[] args) {
		Handler handlerA = new ConcreteHandlerA();
		Handler handlerB = new ConcreteHandlerB();
		Handler handlerC = new ConcreteHandlerC();

		handlerA.setNextHandler(handlerB);
		handlerB.setNextHandler(handlerC);

		Request request = new Request();
		handlerA.handleRequest(request);
	}

}

// 封装请求
class Request {

	public void execute(String name) {
		System.out.println(this + "-" + name + ".");
	}

}

// 封装响应
class Response {
	private final String result;
	public void execute() {
		System.out.println(this + "." + result);
	}

	public Response(String result) {
		this.result = result;
	}
}

abstract class Handler {
	/**
	 * 持有后继的责任对象
	 */
	protected Handler next;

	/**
	 * 示意处理请求的方法，虽然这个示意方法是没有传入参数的 但实际是可以传入参数的，根据具体需要来选择是否传递参数
	 */
	public abstract Response handleRequest(Request req);

	/**
	 * 取值方法
	 */
	public Handler getNextHandler() {
		return next;
	}

	/**
	 * 赋值方法，设置后继的责任对象
	 */
	public void setNextHandler(Handler next) {
		this.next = next;
	}

}

class ConcreteHandlerA extends Handler {

	@Override
	public Response handleRequest(Request req) {
		/**
		 * 判断是否有后继的责任对象,如果有，就转发请求给后继的责任对象 如果没有，则处理请求
		 */
		if (getNextHandler() != null) {
			System.out.println("有后继的责任链对象，不处理本次请求...A");
			getNextHandler().handleRequest(req);
		} else {
			System.out.println("没有后继的责任链对象，处理本次请求...A");
			req.execute(this.getClass().getName());
		}
		return new Response(this.getClass().getName());
	}

}

class ConcreteHandlerB extends Handler {

	@Override
	public Response handleRequest(Request req) {
		if (getNextHandler() != null) {
			System.out.println("有后继的责任链对象，不处理本次请求...B");
			getNextHandler().handleRequest(req);
		} else {
			System.out.println("没有后继的责任链对象，处理本次请求...B");
			req.execute(this.getClass().getName());
		}
		return new Response(this.getClass().getName());
	}
}

class ConcreteHandlerC extends Handler {

	@Override
	public Response handleRequest(Request req) {
		if (getNextHandler() != null) {
			System.out.println("有后继的责任链对象，不处理本次请求...C");
			getNextHandler().handleRequest(req);
		} else {
			System.out.println("没有后继的责任链对象，处理本次请求...C");
			req.execute(this.getClass().getName());
		}
		return new Response(this.getClass().getName());
	}
}
