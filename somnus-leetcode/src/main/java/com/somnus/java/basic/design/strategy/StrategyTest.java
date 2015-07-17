package com.somnus.java.basic.design.strategy;

/**
 * 封装类（Context）：也叫上下文，对策略进行二次封装，目的是避免高层模块对策略的直接调用。
 * 抽象策略：通常情况下为一个接口，当各个实现类中存在着重复的逻辑时，则使用抽象类来封装这部分公共的代码，此时，策略模式看上去更像是模版方法模式。
 * 具体策略：具体策略角色通常由一组封装了算法的类来担任，这些类之间可以根据需要自由替换。
 * 
 * @author lenovo
 *
 */
public class StrategyTest {
	
	public static void main(String[] args) {
		Context context = new Context(new ConcreteStrategy1());
		context.execute();
		
		context = new Context(new ConcreteStrategy2());
		context.execute();
	}

}
