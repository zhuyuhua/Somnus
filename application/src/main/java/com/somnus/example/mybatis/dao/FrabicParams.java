/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.example.mybatis.dao;


/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年3月26日 下午1:51:03
 * @version 0.0.1
 */
public class FrabicParams {

	private boolean isReadOnly;

	private String shardTable;

	private String shardKey;

	public String getShardTable() {
		return shardTable;
	}

	public void setShardTable(String shardTable) {
		this.shardTable = shardTable;
	}

	public String getShardKey() {
		return shardKey;
	}

	public void setShardKey(String shardKey) {
		this.shardKey = shardKey;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

}
