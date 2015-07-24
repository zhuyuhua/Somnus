/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop 
 * @FileName:BaseAfterThrowsAdvice.java 
 * @Date:2014-1-24 上午9:35:15 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/** 
 * @ClassName:BaseAfterThrowsAdvice 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-1-24 上午9:35:15 
 * @Since:V 1.0 
 */
public class BaseAfterThrowsAdvice implements ThrowsAdvice
{
	/**
	 * 通知方法，需要按照这种格式书写
	 * 
	 * @param method
	 *            可选：切入的方法
	 * @param args
	 *            可选：切入的方法的参数
	 * @param target
	 *            可选：目标对象
	 * @param throwable
	 *            必填 : 异常子类，出现这个异常类的子类，则会进入这个通知。
	 */
	public void afterThrowing(Method method, Object[] args, Object target,
			Throwable throwable) {
		System.out.println("======BaseAfterThrowsAdvice：删除出错啦");
	}

}
