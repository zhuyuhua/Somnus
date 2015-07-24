/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop 
 * @FileName:TestAop.java 
 * @Date:2014-1-26 上午11:06:27 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.somnus.example.spring.aop.business.IBaseBusiness;
import com.somnus.example.spring.aop.business.annotation.AnnotationBusiness;
import com.somnus.example.spring.aop.business.aspect.AspectBusiness;
import com.somnus.example.spring.controller.LoginControllerTest;

/**
 * @ClassName:TestAop
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-1-26 上午11:06:27
 * @Since:V 1.0
 */
public class TestAop {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				LoginControllerTest.classPath);
		testAspectAop(context);
	}

	public static void testBaseAop(ApplicationContext context) {
		AspectBusiness business = (AspectBusiness) context
				.getBean("aspectBusiness");
		business.delete("猫");

	}

	public static void testSpringAop(ApplicationContext context) {
		IBaseBusiness business = (IBaseBusiness) context
				.getBean("businessProxy");
		// business.delete("猫");
		business.add("猫");
		// business.modify("猫");

		// AspectBusiness aspectBusiness = (AspectBusiness) context
		// .getBean("aspectBusiness");
		// aspectBusiness.delete("狗+++++");
	}

	public static void testAspectAop(ApplicationContext context) {
		AnnotationBusiness business = (AnnotationBusiness) context
				.getBean("annotationBusiness");
		business.add("miao!!");
	}
}
