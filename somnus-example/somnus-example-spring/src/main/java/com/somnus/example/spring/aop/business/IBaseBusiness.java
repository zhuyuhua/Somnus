/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.service 
 * @FileName:IBaseBusiness.java 
 * @Date:2014-1-24 上午9:18:14 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.aop.business;


/** 
 * @ClassName:IBaseBusiness 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-1-24 上午9:18:14 
 * @Since:V 1.0 
 */
public interface IBaseBusiness
{
	/**
	 * 用作代理的切入点方法
	 * 
	 * @param obj
	 * @return
	 */
	public String delete(String obj);

	/**
	 * 这方法不被切面切
	 * 
	 * @param obj
	 * @return
	 */
	public String add(String obj);

	/**
	 * 这方法切不切呢？可以设置
	 * 
	 * @param obj
	 * @return
	 */
	public String modify(String obj);
}
