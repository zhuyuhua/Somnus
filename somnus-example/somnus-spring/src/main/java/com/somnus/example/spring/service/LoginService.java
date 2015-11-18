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

package com.somnus.example.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
// @Service(value = "loginS")
public class LoginService {
	private static final Logger logger = LogManager.getLogger(LoginService.class);

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
