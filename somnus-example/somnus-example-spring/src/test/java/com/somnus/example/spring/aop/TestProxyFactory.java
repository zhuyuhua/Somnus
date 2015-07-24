/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop 
 * @FileName:TestProxyFactory.java 
 * @Date:2014-2-17 上午10:09:28 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
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
 * @ClassName:TestProxyFactory
 * @Desc:ProxyFactory测试用例
 * @Author:joe
 * @Date:2014-2-17 上午10:09:28
 * @Since:V 1.0
 */
public class TestProxyFactory
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = LogManager
			.getLogger(TestProxyFactory.class);

	public static void main(String[] args) {
		IBaseBusiness baseBusiness = new BaseBusiness();
		ProxyFactory factory = new ProxyFactory(baseBusiness);
		// factory.addAdvice(new BaseBeforeAdvice());

		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(
				new Pointcut(), new BaseBeforeAdvice());
		factory.addAdvisor(advisor);
		IBaseBusiness businessProxy = (IBaseBusiness) factory.getProxy();
		businessProxy.modify("aaa");

	}
}
