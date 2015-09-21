/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pingan.karma.store.base.HBaseStore;

/**
 * TODO
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public interface EventStore {

	<T> void putObject(T t,String tableName,String identifier);
	
	<T> T getObject(String tableName,String identifier,String className);
	
	void setHbaseStore(HBaseStore hbaseStore);
}
