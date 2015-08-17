/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.proxy 
 * @FileName:TestStaticProxy.java 
 * @Date:2014-2-14 下午2:46:47 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class TestStaticProxy {
	private static final Logger logger = LogManager.getLogger(TestStaticProxy.class);

	public static void main(String[] args) {
		CountProxy proxy = new CountProxy(new CountImpl());
		proxy.queryCount();
		proxy.updateCount();

	}

}

interface Count {
	public void queryCount();

	public void updateCount();
}

class CountImpl implements Count {

	@Override
	public void queryCount() {
		System.out.println("查看账户的方法...");
	}

	@Override
	public void updateCount() {
		System.out.println("修改账户的方法...");
	}
}

class CountProxy implements Count {
	private final Count count;

	public CountProxy(Count count) {
		this.count = count;
	}

	@Override
	public void queryCount() {
		System.out.println("调用querycount前执行");
		count.queryCount();
		System.out.println("调用querycount后执行");
	}

	@Override
	public void updateCount() {
		System.out.println("调用updatecount前执行");
		count.updateCount();
		System.out.println("调用updatecount后执行");
	}

}
