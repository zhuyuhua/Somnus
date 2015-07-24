package com.somnus.example.spring.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.somnus.example.spring.dao.UserDAO;
import com.somnus.example.spring.model.User;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LogManager
			.getLogger(UserDAOImpl.class);

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
//		testError();
	}

	private void testError() {
		throw new RuntimeException("阻止数据库提交！");
	}

}
