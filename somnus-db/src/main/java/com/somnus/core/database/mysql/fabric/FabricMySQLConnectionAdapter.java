/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.core.database.mysql.fabric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.fabric.jdbc.FabricMySQLConnection;
import com.mysql.fabric.jdbc.JDBC4FabricMySQLConnection;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class FabricMySQLConnectionAdapter {

	private static Logger logger = LoggerFactory.getLogger(FabricMySQLConnectionAdapter.class);

	private JDBC4FabricMySQLConnection jdbc4FabricMySQLConnection;// 兼容JDK1.6+

	private FabricMySQLConnection fabricMySQLConnection;// 兼容JDK1.5+

}
