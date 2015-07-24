/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.service 
 * @FileName:BaseBusiness.java 
 * @Date:2014-1-24 上午9:18:42 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop.business;

/**
 * 
 * @author zhuyuhua
 * @version 1.0
 */
public class BaseBusiness implements IBaseBusiness {
	/**
	 * 切入点
	 * 
	 * @throws Exception
	 */
	@Override
	public String delete(String obj) {
		System.out.println("======调用delete切入点：" + obj + "说：你敢删除我！===========");
		return obj + "：瞄～";
	}

	@Override
	public String add(String obj) {
		// System.out.println("=========这个方法不能被切。。。========");
		System.out.println("======调用add切入点：" + obj + ",返回值=" + obj + "：瞄～ 嘿嘿！");
		return obj + "：瞄～ 嘿嘿！";
	}

	@Override
	public String modify(String obj) {
		// System.out.println("=========这个也设置加入切吧============");
		System.out.println("======调用modify切入点：" + obj);
		return obj + "：瞄改瞄啊！";
	}

}
