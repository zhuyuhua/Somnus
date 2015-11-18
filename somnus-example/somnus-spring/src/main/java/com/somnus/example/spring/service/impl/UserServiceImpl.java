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

package com.somnus.example.spring.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.somnus.example.spring.dao.LogInfoDAO;
import com.somnus.example.spring.dao.UserDAO;
import com.somnus.example.spring.model.User;
import com.somnus.example.spring.service.UserService;

/**
 * @author zhuyuhua
 * @version 0.0.1
 */
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private UserDAO userDAO;
	private LogInfoDAO logInfoDAO;

	public void setLogInfoDAO(LogInfoDAO logInfoDAO) {
		this.logInfoDAO = logInfoDAO;
	}

	// @Resource(name = "user")
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		// this.userDAO.save(user);
		// LogInfo info = new LogInfo();
		// info.setMsg("add user:name=" + user.getUserName());
		// this.logInfoDAO.save(info);
	}

	@Override
	public void callUser() {
		logger.debug("==");
	}

}
