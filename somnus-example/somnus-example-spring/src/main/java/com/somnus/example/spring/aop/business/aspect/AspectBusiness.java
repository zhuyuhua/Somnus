/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop.aspectj 
 * @FileName:AspectBusiness.java 
 * @Date:2014-1-24 上午11:22:44 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop.business.aspect;

/**
 * 
 * @author zhuyuhua
 * @version 1.0
 */
public class AspectBusiness {
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
