/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.test 
 * @FileName:CallBack.java 
 * @Date:2014-1-16 下午2:06:54 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.test;

import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;




/** 
 * @ClassName:CallBack 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-1-16 下午2:06:54 
 * @Since:V 1.0 
 */
public class MainClass
{
	public static void main(String[] args) {

		System.out
				.println(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		Assert.notNull(null, "URL path must not be null");
	}
}


