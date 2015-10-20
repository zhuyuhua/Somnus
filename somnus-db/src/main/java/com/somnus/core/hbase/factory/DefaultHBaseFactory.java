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
package com.somnus.core.hbase.factory;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Closeables;
import com.somnus.core.bigdata.utils.ConfigUtil;
import com.somnus.core.bigdata.utils.HBCheckUtil;

public class DefaultHBaseFactory implements HBaseFactory {

	static Logger logger = LoggerFactory.getLogger(DefaultHBaseFactory.class);

	private HConnection hConnection;

	private HBaseAdmin hBaseAdmin;

	public DefaultHBaseFactory() {
				
		try {
			ConfigUtil.setHadoopConfig();
			Configuration config = ConfigUtil.getHBConfig();
			hConnection = HConnectionManager.createConnection(config);
			hBaseAdmin=new HBaseAdmin(hConnection);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public HBaseAdmin getHBaseAdmin() {
		return hBaseAdmin;
	}

	@Override
	public HTableInterface getHTable(String tableName) {
		HTableInterface table = null;
		try {
			table = hConnection.getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return table;
	}

	@Override
	public boolean deleteTable(String tableName) {
		HBCheckUtil.checkArgument(tableName);
		try {
			hBaseAdmin.disableTable(tableName);
			hBaseAdmin.deleteTable(tableName);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
//			return false;
		}
		return true;
	}

	@Override
	public HTableDescriptor createTable(String tableName, String[] family) {

		try {
			boolean tableExists = hBaseAdmin.tableExists(tableName);
			HBCheckUtil.checkArgument(tableExists,
					"the table [%s] should not exist.", tableName);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}

		HTableDescriptor tableDesc = new HTableDescriptor(tableName);
		for (int i = 0; i < family.length; i++) {
			tableDesc.addFamily(new HColumnDescriptor(family[i]));
		}
		
		
		try {
			hBaseAdmin.createTable(tableDesc);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}

		return tableDesc;
	}

	@Override
	public void closeHTable(HTableInterface hTableInterface) {
		Closeables.closeQuietly(hTableInterface);
	}

	@Override
	public void destroy() {
		Closeables.closeQuietly(hBaseAdmin);
		Closeables.closeQuietly(hConnection);
	}

}
