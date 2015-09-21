/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.store;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pingan.karma.domain.Attribute;
import com.pingan.karma.store.base.HBaseStore;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class GenericEventStore implements EventStore {

	private static Logger logger = LoggerFactory
			.getLogger(GenericEventStore.class);
	private HBaseStore hbaseStore;

	@Override
	public <T> void putObject(T t, String tableName, String identifier) {
		List<Attribute> attributes = ReflectUtil.getAttributes(t);
		String rowKey = t.getClass().getName() + identifier;
		hbaseStore.put2Family(tableName, rowKey, "attr", attributes);
	}

	public void setHbaseStore(HBaseStore hbaseStore) {
		this.hbaseStore = hbaseStore;
	}

	@Override
	public <T> T getObject(String tableName, String identifier, String className) {
		String rowKey = className + identifier;
		T t = null;
		try {
			Map<String, Attribute> allAttribute = hbaseStore.getAttrsByFamily(
					tableName, rowKey, "attr");
			t = ReflectUtil.getObject(allAttribute, className);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}

		return t;
	}

}
