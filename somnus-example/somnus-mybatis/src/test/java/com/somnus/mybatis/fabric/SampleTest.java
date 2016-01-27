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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.fabric.jdbc.FabricMySQLConnection;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月29日
 */
public class SampleTest {

	private static final Logger logger = LogManager.getLogger(SampleTest.class);

	public static void main(String args[]) throws Exception {

		String hostname = System.getProperty("com.mysql.fabric.testsuite.hostname");
		String port = System.getProperty("com.mysql.fabric.testsuite.port");
		String database = System.getProperty("com.mysql.fabric.testsuite.database");
		String user = System.getProperty("com.mysql.fabric.testsuite.username");
		String password = System.getProperty("com.mysql.fabric.testsuite.password");

		String baseUrl = "jdbc:mysql:fabric://" + hostname + ":" + Integer.valueOf(port) + "/";

		// Load the driver if running under Java 5
		if (!com.mysql.jdbc.Util.isJdbc4()) {
			Class.forName("com.mysql.fabric.jdbc.FabricMySQLDriver");
		}

		// 1. Create database and table for our demo
		Connection rawConnection = DriverManager.getConnection(
				baseUrl + "mysql?fabricServerGroup=fabric_test1_global",
				user,
				password);
		Statement statement = rawConnection.createStatement();
		statement.executeUpdate("create database if not exists employees");
		statement.close();
		rawConnection.close();

		// We should connect to the global group to run DDL statements,
		// they will be replicated to the server groups for all shards.

		// The 1-st way is to set it's name explicitly via the
		// "fabricServerGroup" connection property
		rawConnection = DriverManager.getConnection(
				baseUrl + database + "?fabricServerGroup=fabric_test1_global",
				user,
				password);
		statement = rawConnection.createStatement();
		statement.executeUpdate("create database if not exists employees");
		statement.close();
		rawConnection.close();

		// The 2-nd way is to get implicitly connected to global group
		// when the shard key isn't provided, ie. set "fabricShardTable"
		// connection property but don't set "fabricShardKey"
		rawConnection = DriverManager.getConnection(
				baseUrl + "employees" + "?fabricShardTable=employees.employees",
				user,
				password);
		// At this point, we have a connection to the global group for 
		// the `employees.employees' shard mapping.
		statement = rawConnection.createStatement();
		statement.executeUpdate("drop table if exists employees");
		statement.executeUpdate("create table employees (emp_no int not null," + 
		          "first_name varchar(50), last_name varchar(50)," +
				  "primary key (emp_no))");

		// 2. Insert data

		// Cast to a Fabric connection to have access to specific methods
		FabricMySQLConnection connection = (FabricMySQLConnection)rawConnection;

		// example data used to create employee records
		Integer ids[] = new Integer[] {1, 2, 10001, 10002};
		String firstNames[] = new String[] {"John", "Jane", "Andy", "Alice"};
		String lastNames[] = new String[] {"Doe", "Doe", "Wiley", "Wein"};

		// insert employee data
		PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO employees.employees VALUES (?,?,?)");
		for (int i = 0; i < 4; ++i) {
			// choose the shard that handles the data we interested in
			connection.setShardKey(ids[i].toString());

			// perform insert in standard fashion
			ps.setInt(1, ids[i]);
			ps.setString(2, firstNames[i]);
			ps.setString(3, lastNames[i]);
			ps.executeUpdate();
		}

		// 3. Query the data from employees
		System.out.println("Querying employees");
		System.out.format("%7s | %-30s | %-30s%n", "emp_no", "first_name", "last_name");
		System.out.println("--------+--------------------------------+-------------------------------");
		ps = connection.prepareStatement(
            "select emp_no, first_name, last_name from employees where emp_no = ?");
		for (int i = 0; i < 4; ++i) {

			// we need to specify the shard key before accessing the data
			connection.setShardKey(ids[i].toString());

			ps.setInt(1, ids[i]);
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.format("%7d | %-30s | %-30s%n", rs.getInt(1), rs.getString(2), rs.getString(3));
			rs.close();
		}
		ps.close();

		// 4. Connect to the global group and clean up
		connection.setServerGroupName("fabric_test1_global");
		statement.executeUpdate("drop table if exists employees");
		statement.close();
		connection.close();
	}
}
