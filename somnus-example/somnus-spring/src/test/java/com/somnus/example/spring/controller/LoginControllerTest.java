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

package com.somnus.example.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.somnus.example.spring.aop.business.IBaseBusiness;
import com.somnus.example.spring.aop.business.aspect.AspectBusiness;
import com.somnus.example.spring.core.SpringTransactionalTestCase;
import com.somnus.example.spring.service.LoginService;

public class LoginControllerTest extends SpringTransactionalTestCase {

	private static final Logger logger = LogManager.getLogger(LoginControllerTest.class);

	@Autowired
	private LoginService loginService;

	public static String xmlPath = "F:\\eclipse\\java_workspace\\myspring\\src\\test\\resources\\applicationContext-test.xml";

	public static String classPath = "classpath*:springcfg/application*.xml";

	private ApplicationContext context;

	@Test
	public void test() {
		// context = new FileSystemXmlApplicationContext(xmlPath);
		//
		// LoginService service = context.getBean(LoginService.class);
		// service.showLogin();
		// loginService.showLogin();

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		// Resource resource = new
		// ClassPathResource("applicationContext-test.xml");
		// DefaultListableBeanFactory factory = new
		// DefaultListableBeanFactory();
		// XmlBeanDefinitionReader reader = new
		// XmlBeanDefinitionReader(factory);
		// reader.loadBeanDefinitions(resource);
		//
		// LoginService service = (LoginService) factory.getBean("loginS");
		// service.showLogin();

		ApplicationContext context = new ClassPathXmlApplicationContext(classPath);

		IBaseBusiness business = (IBaseBusiness) context.getBean("businessProxy");
		business.delete("猫");

		AspectBusiness aspectBusiness = (AspectBusiness) context.getBean("aspectBusiness");
		aspectBusiness.delete("狗+++++");
		logger.debug("=======");
	}

}
