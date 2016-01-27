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
package com.somnus.mybatis.fabric;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.fabric.jdbc.JDBC4FabricMySQLConnection;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月29日
 */
public class MySQLFabricTest {

	private static final Logger logger = LogManager.getLogger(MySQLFabricTest.class);

	static String user = "root";
	static String password = "root";
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.fabric.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(
		        "jdbc:mysql:fabric://fabrichost:32274/database",
		        user,
		        password);
		
		JDBC4FabricMySQLConnection fabricConn = (JDBC4FabricMySQLConnection)conn;
		
		System.out.println(conn);
		
		//要么serverGroup
		fabricConn.setServerGroupName("myGroup");
		
		//要么sharedtable and shared key
		fabricConn.addQueryTable("employees");
		fabricConn.setShardKey("40"); // work with data related to shard key = 40
		
		
		
	}
}
