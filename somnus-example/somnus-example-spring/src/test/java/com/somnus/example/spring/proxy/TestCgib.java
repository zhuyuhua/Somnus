/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.proxy 
 * @FileName:TestCgib.java 
 * @Date:2014-2-14 下午3:06:20 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/** 
 * @ClassName:TestCgib 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-2-14 下午3:06:20 
 * @Since:V 1.0 
 */
public class TestCgib
{
	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacade book = (BookFacade) cglib.getInstance(new BookFacade());
		System.out.println(book.addBook());
	}
}

class BookFacade
{
	public String addBook() {
		System.out.println("增加图书的方法...");
		return "11";
	}
}

class BookFacadeCglib implements MethodInterceptor
{
	private Object target;

	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		//
		enhancer.setSuperclass(this.target.getClass());
		// 回调方法
		enhancer.setCallback(this);
		// 创建代理对象
		return enhancer.create();

	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		System.out.println("事务开始");
		Object val = proxy.invokeSuper(obj, args);
		// Object val = proxy.invoke(obj, args);
		System.out.println("事务结束");
		return val;
	}

}
