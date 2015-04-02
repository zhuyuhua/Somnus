/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.example.mybatis.dao;

import org.springframework.stereotype.Repository;

import com.somnus.example.mybatis.entity.User;

/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年3月26日 上午11:55:34
 * @version 0.0.1
 */
@Repository
public class UserDao extends BaseDao {


	public void save(User u) {
		// getSqlSession().insert("insert-user", u);
		getFabricSqlSession(new FrabicParams()).insert("insert-user", u);
	}
}
