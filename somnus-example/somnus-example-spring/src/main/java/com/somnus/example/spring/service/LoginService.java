/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring 
 * @FileName:LoginService.java 
 * @Date:2014-1-10 上午9:32:28 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** 
 * @ClassName:LoginService 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-1-10 上午9:32:28 
 * @Since:V 1.0 
 */
// @Service(value = "loginS")
public class LoginService
{
	private static final Logger logger = LogManager
			.getLogger(LoginService.class);

	public void showLogin() {
		logger.debug(this.toString());
		userService.callUser();
	}

	private UserService userService;

	/**
	 * @return userService
	 * @since 1.0.0
	 */

	public UserService getUserService() {
		return userService;
	}

	/**
	 * set the userService
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
