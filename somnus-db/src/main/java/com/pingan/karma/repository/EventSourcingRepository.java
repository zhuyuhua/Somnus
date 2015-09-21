/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.repository;

import java.util.Map;

import com.pingan.karma.domain.Account;
import com.pingan.karma.domain.Attribute;
import com.pingan.karma.factory.HBaseFactory;
import com.pingan.karma.factory.KarmaHBaseFactory;
import com.pingan.karma.store.EventStore;
import com.pingan.karma.store.base.HBaseStore;
import com.pingan.karma.store.base.KarmaHBaseStore;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 * @param <T>
 */
public class EventSourcingRepository<T> implements Repository<T> {

	private EventStore eventStore;

	public EventSourcingRepository() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pingan.karma.repository.HBRepository#persist(java.lang.String,
	 * java.lang.Object, java.lang.String)
	 */
	@Override
	public void persist(String tableName, String identifier, T object) {
		// TODO Auto-generated method stub
		eventStore.putObject(object, tableName, identifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pingan.karma.repository.HBRepository#getObject(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public T getObject(String tableName, String identifier, String className) {
		
		T t = eventStore.getObject(tableName, identifier, className);
		return t;
	}

	public void setEventStore(EventStore eventStore) {
		this.eventStore = eventStore;
	}

}
