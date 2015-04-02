/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.example.mybatis.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mysql.fabric.jdbc.JDBC4FabricMySQLConnection;

/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年3月26日 下午1:53:21
 * @version 0.0.1
 */
public abstract class BaseDao extends SqlSessionDaoSupport {

	public SqlSession getFabricSqlSession(FrabicParams params) {

		SqlSession session = getSqlSession();
		try {
			Connection conn = session.getConnection();
		System.out.println(conn);

		JDBC4FabricMySQLConnection connFabric = conn
				.unwrap(JDBC4FabricMySQLConnection.class);
		//
		// connFabric.setReadOnly(params.isReadOnly());
		// connFabric.setShardTable(params.getShardTable());
		// connFabric.setShardKey(params.getShardKey());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return session;
	}
}
