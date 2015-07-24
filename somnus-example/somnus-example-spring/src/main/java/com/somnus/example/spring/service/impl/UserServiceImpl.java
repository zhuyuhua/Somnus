package com.somnus.example.spring.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.somnus.example.spring.dao.LogInfoDAO;
import com.somnus.example.spring.dao.UserDAO;
import com.somnus.example.spring.model.User;
import com.somnus.example.spring.service.UserService;

public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager
			.getLogger(UserServiceImpl.class);

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
		this.userDAO.save(user);
		// LogInfo info = new LogInfo();
		// info.setMsg("add user:name=" + user.getUserName());
		// this.logInfoDAO.save(info);
	}

	@Override
	public void callUser() {
		logger.debug("==");
	}

}
