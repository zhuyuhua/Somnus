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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.somnus.example.spring.aop.business.IBaseBusiness;
import com.somnus.example.spring.aop.business.annotation.AnnotationBusiness;
import com.somnus.example.spring.aop.business.aspect.AspectBusiness;
import com.somnus.example.spring.controller.LoginControllerTest;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class TestAop {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext(LoginControllerTest.classPath);
		testAspectAop(context);
	}

	public static void testBaseAop(ApplicationContext context) {
		AspectBusiness business = (AspectBusiness) context.getBean("aspectBusiness");
		business.delete("猫");

	}

	public static void testSpringAop(ApplicationContext context) {
		IBaseBusiness business = (IBaseBusiness) context.getBean("businessProxy");
		// business.delete("猫");
		business.add("猫");
		// business.modify("猫");

		// AspectBusiness aspectBusiness = (AspectBusiness) context
		// .getBean("aspectBusiness");
		// aspectBusiness.delete("狗+++++");
	}

	public static void testAspectAop(ApplicationContext context) {
		AnnotationBusiness business = (AnnotationBusiness) context.getBean("annotationBusiness");
		business.add("miao!!");
	}
}
