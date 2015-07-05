package com.somnus.core.basic.design.adapter;

/**
 * 定义：将一个类的接口转换成客户希望的另一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 
 * 目标接口（Target）：客户所期待的接口。目标可以是具体的或抽象的类，也可以是接口。
 * 
 * 需要适配的类（Adaptee）：需要适配的类或适配者类。
 * 
 * 适配器（Adapter）：通过包装一个需要适配的对象，把原接口转换成目标接口。
 * 
 * 客户端（Client）：与符合Target接口的对象协同。
 * 
 * @author joe
 *
 */
public class AdapterClient {

	public static void main(String[] args) {
		Target target = new Adapter();

		target.request();
	}

}
