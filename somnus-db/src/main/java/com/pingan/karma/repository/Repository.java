/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.repository;

import com.pingan.karma.store.EventStore;



/**
 * TODO
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public interface Repository<T> {
	
	void persist(String tableName,String identifier,T object);
	
	T getObject(String tableName,String identifier,String className);
	
	 void setEventStore(EventStore eventStore);
}
