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

package com.somnus.example.spring.aop.advice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class AspectAdvice {
	/**
	 * 前置通知
	 * 
	 * @param jp
	 */
	public void doBefore(JoinPoint jp) {
		System.out.println("===========进入AspectAdvice.doBefore============ \n");

		System.out.print("准备在" + jp.getTarget().getClass() + "对象上用");
		System.out.print(jp.getSignature().getName() + "方法进行对 '");
		System.out.print(jp.getArgs()[0] + "'进行删除！\n\n");

		System.out.println("要进入切入点方法了 \n");
	}

	/**
	 * 后置通知
	 * 
	 * @param jp
	 *            连接点
	 * @param result
	 *            返回值
	 */
	public void doAfter(JoinPoint jp, String result) {
		System.out.println("==========AspectAdvice.doAfter=========== \n");
		System.out.println("切入点方法执行完了 \n");

		System.out.print(jp.getArgs()[0] + "在");
		System.out.print(jp.getTarget().getClass() + "对象上被");
		System.out.print(jp.getSignature().getName() + "方法删除了");
		System.out.print("只留下：" + result + "\n\n");
	}

	/**
	 * 环绕通知
	 * 
	 * @param pjp
	 *            连接点
	 */
	public void doAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========AspectAdvice.doAround=========== \n");

		// 调用目标方法之前执行的动作
		System.out.println("调用方法之前: 执行！\n");

		// 调用方法的参数
		Object[] args = pjp.getArgs();
		// 调用的方法名
		String method = pjp.getSignature().getName();
		// 获取目标对象
		Object target = pjp.getTarget();
		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
		Object result = pjp.proceed();

		System.out.println("输出：" + args[0] + ";" + method + ";" + target + ";" + result + "\n");
		System.out.println("调用方法结束：之后执行！\n");
	}

	/**
	 * 异常通知
	 * 
	 * @param jp
	 * @param e
	 */
	public void doThrow(JoinPoint jp, Throwable e) {
		System.out.println("删除出错啦");
	}
}
