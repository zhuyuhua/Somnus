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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class BaseBeforeAdvice implements MethodBeforeAdvice {
	private static final Logger LOGGER = LogManager.getLogger(BaseBeforeAdvice.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.
	 * Method , java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("========进入BaseBeforeAdvice.beforeAdvice()========");

		System.out.println("准备在" + target + "对象上用" + method + "方法进行对 '" + args[0] + "'进行删除！");

		System.out.println("========退出BaseBeforeAdvice.beforeAdvice()========");
	}
}
