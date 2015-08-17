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

package com.somnus.example.spring.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.somnus.example.spring.dao.UserDAO;
import com.somnus.example.spring.model.User;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(User user) {
		logger.debug(user);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);

		session.getTransaction().commit();
		session.close();
		// testError();
	}

	private void testError() {
		throw new RuntimeException("阻止数据库提交！");
	}

}
