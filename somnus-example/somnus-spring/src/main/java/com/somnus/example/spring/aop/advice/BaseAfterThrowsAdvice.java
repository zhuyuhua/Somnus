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

import org.springframework.aop.ThrowsAdvice;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class BaseAfterThrowsAdvice implements ThrowsAdvice {
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
	public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable) {
		System.out.println("======BaseAfterThrowsAdvice：删除出错啦");
	}

}
