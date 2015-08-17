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

package com.somnus.example.spring.aop.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * @ClassName:Pointcut
 * @Desc:定义一个切点，指定对应方法匹配。来供切面来针对方法进行处理<br>
 * @Author:joe
 * @Date:2014-1-24 上午9:36:22
 * @Since:V 1.0
 */
public class Pointcut extends NameMatchMethodPointcut {

	/**
	 * @Fileds:serialVersionUID:TODO
	 * 
	 */

	private static final long serialVersionUID = -4254399430245393182L;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean matches(Method method, Class targetClass) {
		// System.out.println("======Pointcut.mathes======");
		// 设置单个方法匹配
		// this.setMappedName("delete");
		// 设置多个方法匹配
		String[] methods = { "delete1", "add1" };

		// 也可以用“ * ” 来做匹配符号
		// this.setMappedName("get*");

		this.setMappedNames(methods);

		return super.matches(method, targetClass);
	}

}
