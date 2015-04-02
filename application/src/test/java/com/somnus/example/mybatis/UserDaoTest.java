/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.example.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.somnus.example.mybatis.dao.UserDao;
import com.somnus.example.mybatis.entity.User;

/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年3月26日 下午12:15:54
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/application-context.xml" })
public class UserDaoTest {

	private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	private UserDao userDao;

	@Test
	public void testInsert() {
		for (int i = 0; i < 1; i++) {
			User u = new User();
			u.setUserName("zhuyuhua");
			u.setPassword("sb");
			userDao.save(u);
		}
		System.out.println("保存成功！");
	}

}
