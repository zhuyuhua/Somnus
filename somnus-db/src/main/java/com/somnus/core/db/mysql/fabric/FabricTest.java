/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.core.db.mysql.fabric;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.fabric.jdbc.JDBC4FabricMySQLConnection;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class FabricTest {

	private static Logger logger = LoggerFactory.getLogger(FabricTest.class);

	private static String user = null;

	private static String password = null;

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.fabric.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql:fabric://fabrichost:32274/database", user, password);

		JDBC4FabricMySQLConnection fabricMySQLConnection = (JDBC4FabricMySQLConnection) conn;

		fabricMySQLConnection.setServerGroupName("myGroup");

		// 提供share table和share key
		// Connection conn = DriverManager.getConnection(
		// "jdbc:mysql:fabric://fabrichost:32274/database?fabricShardTable=employees.employees");
	}

}
