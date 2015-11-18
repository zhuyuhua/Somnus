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

package com.somnus.example.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.somnus.example.spring.aop.advice.BaseBeforeAdvice;
import com.somnus.example.spring.aop.business.BaseBusiness;
import com.somnus.example.spring.aop.business.IBaseBusiness;
import com.somnus.example.spring.aop.pointcut.Pointcut;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class TestProxyFactory {
	/**
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = LogManager.getLogger(TestProxyFactory.class);

	public static void main(String[] args) {
		IBaseBusiness baseBusiness = new BaseBusiness();
		ProxyFactory factory = new ProxyFactory(baseBusiness);
		// factory.addAdvice(new BaseBeforeAdvice());

		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new Pointcut(), new BaseBeforeAdvice());
		factory.addAdvisor(advisor);
		IBaseBusiness businessProxy = (IBaseBusiness) factory.getProxy();
		businessProxy.modify("aaa");

	}
}
