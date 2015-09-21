package com.pingan.karma.factory;


public interface HBaseFactory {

	/**
	 * ͨ�� tableName ����ȡ��ￄ1�7 Table
	 */
	HTableInterface getHTable(String tableName);

	HBaseAdmin getHBaseAdmin();

	/**
	 * �ر�ĳ��table
	 */
	void closeHTable(HTableInterface hTableInterface);

	/** only for unit test */
	boolean deleteTable(String tableName);

	/** only for unit test */
	HTableDescriptor createTable(String tableName, String[] family);

	void destroy();
}
