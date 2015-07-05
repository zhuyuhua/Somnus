package com.somnus.core.basic.design.template;

/**
 * AbstractClass：抽象类。用来定义算法骨架和原语操作，具体的子类可以通过重定义这些原语操作来实现一个算法的各个步骤。在这个类里面，还可以提供算法中通用的实现。
 * ConcreteClass：具体实现类。用来实现算法骨架中的某些步骤，完成与特定子类相关的功能。
 */

public class TemplateTest {

	public static void main(String[] args) {
		AbstractClass test = new ConcreteClass();
		test.execute();
	}

}
