/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.store;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pingan.karma.domain.Attribute;
import com.pingan.karma.factory.HBaseFactory;
import com.pingan.karma.factory.KarmaHBaseFactory;
import com.pingan.karma.store.base.HBaseStore;
import com.pingan.karma.store.base.KarmaHBaseStore;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class KarmaHBaseStoreTest {

	private static Logger logger = LoggerFactory
			.getLogger(KarmaHBaseStoreTest.class);

	HBaseStore store;

	public KarmaHBaseStoreTest() {
		store = new KarmaHBaseStore();
		HBaseFactory factory = new KarmaHBaseFactory();
		store.setFactory(factory);
	}

	/**
	 * Test method for
	 * {@link com.pingan.karma.store.base.KarmaHBaseStore#putObject(java.lang.String, java.lang.String, java.lang.Object)}
	 * 
	 */
	@Test
	public void testPutObject() {

	}

	/**
	 * Test method for
	 * {@link com.pingan.karma.store.base.KarmaHBaseStore#getAttrsByFamily(java.lang.String, java.lang.String, java.lang.String)}
	 * 
	 */
	@Test
	public void testGetNewValue() {
		Attribute attribute = store.getNewValue("aggregate",
				"com.pingan.karma.domain.Account1", "attr", "age");
		System.out.println(attribute.getName());
		System.out.println(attribute.getValue());
	}

	@Test
	public void testGetRecentValues(){
		List<Attribute> recentValues = store.getRecentValues("aggregate",
				"com.pingan.karma.domain.Account1", "attr", "name", 5);
		for (Attribute attribute : recentValues) {
			System.out.println(attribute);
		}
	}
}
