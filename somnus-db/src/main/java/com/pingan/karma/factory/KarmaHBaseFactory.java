package com.pingan.karma.factory;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.security.UserGroupInformation.HadoopLoginModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Closeables;
import com.somnus.core.bigdata.utils.ConfigUtil;
import com.somnus.core.bigdata.utils.HBCheckUtil;

public class KarmaHBaseFactory implements HBaseFactory {

	static Logger logger = LoggerFactory.getLogger(KarmaHBaseFactory.class);

	private HConnection hConnection;

	private HBaseAdmin hBaseAdmin;

	public KarmaHBaseFactory() {
				
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
