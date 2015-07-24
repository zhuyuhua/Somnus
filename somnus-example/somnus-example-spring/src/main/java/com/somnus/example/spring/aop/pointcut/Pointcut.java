/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.aop 
 * @FileName:Pointcut.java 
 * @Date:2014-1-24 上午9:36:22 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
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
