/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop 
 * @FileName:BaseAroundAdvice.java 
 * @Date:2014-1-24 上午9:34:31 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/** 
 * @ClassName:BaseAroundAdvice 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-1-24 上午9:34:31 
 * @Since:V 1.0 
 */
public class BaseAroundAdvice implements MethodInterceptor
{

	/* (non-Javadoc) 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation) 
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("========进入BaseAroundAdvice.around环绕方法！===========");

		// 调用目标方法之前执行的动作
		System.out.println("调用方法之前: 执行！");

		// 调用方法的参数
		Object[] args = invocation.getArguments();
		// 调用的方法
		Method method = invocation.getMethod();
		// 获取目标对象
		Object target = invocation.getThis();
		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
		Object returnValue = invocation.proceed();

		System.out.print("BaseAroundAdvice:" + args[0] + "在");
		System.out.print(target + "对象上被");
		System.out.print(method + "方法删除了");
		System.out.print("只留下：" + returnValue + "\n");

		System.out
.println("=======结束BaseAroundAdvice.around环绕方法！========");

		return returnValue;
	}
}
