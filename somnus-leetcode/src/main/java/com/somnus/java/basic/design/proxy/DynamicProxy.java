package com.somnus.java.basic.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 
 * @author Administrator
 * 
 */
public class DynamicProxy implements InvocationHandler {

	private Object target;

	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 */
	public Object bind(Object target) {
		this.setTarget(target);
		Class<?> cls = target.getClass();
		// 取得代理对象
		return Proxy.newProxyInstance(cls.getClassLoader(),
				cls.getInterfaces(), this); // 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before request...");
		Object object = method.invoke(target, args);
		System.out.println("after request...,result=" + object);

		return object;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
}
