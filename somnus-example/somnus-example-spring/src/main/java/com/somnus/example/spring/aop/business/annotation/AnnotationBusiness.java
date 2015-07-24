package com.somnus.example.spring.aop.business.annotation;

import org.springframework.stereotype.Component;

@Component(value = "annotationBusiness")
public class AnnotationBusiness {

	/**
	 * 切入点
	 * 
	 * @throws Exception
	 */
	public String delete(String obj) {
		System.out.println("======调用delete切入点：" + obj + "说：你敢删除我！===========");
		return obj + "：瞄～";
	}

	public String add(String obj) {
		// System.out.println("=========这个方法不能被切。。。========");
		System.out.println("======调用add切入点：" + obj + ",返回值=" + obj + "：瞄～ 嘿嘿！");
		return obj + "：瞄～ 嘿嘿！";
	}

	public String modify(String obj) {
		// System.out.println("=========这个也设置加入切吧============");
		System.out.println("======调用modify切入点：" + obj);
		return obj + "：瞄改瞄啊！";
	}
}
