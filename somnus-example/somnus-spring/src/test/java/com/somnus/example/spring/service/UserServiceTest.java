package com.somnus.example.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.somnus.example.spring.core.SpringTransactionalTestCase;
import com.somnus.example.spring.model.User;
import com.somnus.example.spring.service.UserService;

public class UserServiceTest extends SpringTransactionalTestCase {

	private static final Logger logger = LogManager
			.getLogger(UserServiceTest.class);

	@Autowired
	private UserService userService;

	@Test
	public void testAdd() {
		User user = new User();
		user.setUserName("121");
		user.setPassword("password");
		userService.addUser(user);
	}

}
