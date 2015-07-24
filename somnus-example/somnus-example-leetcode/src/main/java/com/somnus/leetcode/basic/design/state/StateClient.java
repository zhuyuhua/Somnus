package com.somnus.leetcode.basic.design.state;

public class StateClient {

	public static void main(String[] args) {
		// 创建状态
		State state = new ConcreteStateA();
		// 创建环境
		Context context = new Context();
		// 将状态设置到环境中
		context.setState(state);
		// 请求
		context.request("test");
	}
}

interface State {
	void handler(String params);
}

class ConcreteStateA implements State {

	@Override
	public void handler(String params) {
		System.out.println("ConcreteStateA handler ：" + params);
	}

}
class Context {

	private State state;

	public void setState(State state) {
		this.state = state;
	}

	public void request(String params) {
		state.handler(params);
	}
}
