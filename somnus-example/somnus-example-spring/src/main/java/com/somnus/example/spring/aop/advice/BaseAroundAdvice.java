/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.somnus.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class BaseAroundAdvice implements MethodInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.
	 * intercept.MethodInvocation)
	 */
	@Override
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

		System.out.println("=======结束BaseAroundAdvice.around环绕方法！========");

		return returnValue;
	}
}
