/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop 
 * @FileName:BaseBeforeAdvice.java 
 * @Date:2014-1-24 上午9:19:15 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @ClassName:BaseBeforeAdvice
 * @Desc:前置通知
 * @Author:joe
 * @Date:2014-1-24 上午9:19:15
 * @Since:V 1.0
 */
public class BaseBeforeAdvice implements MethodBeforeAdvice
{
	private static final Logger LOGGER = LogManager
			.getLogger(BaseBeforeAdvice.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method
	 * , java.lang.Object[], java.lang.Object)
	 */
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("========进入BaseBeforeAdvice.beforeAdvice()========");

		System.out.println("准备在" + target + "对象上用" + method + "方法进行对 '"
				+ args[0] + "'进行删除！");

		System.out.println("========退出BaseBeforeAdvice.beforeAdvice()========");
	}
}
