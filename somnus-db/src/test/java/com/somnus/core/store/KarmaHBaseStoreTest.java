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
package com.somnus.core.store;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.core.hbase.domain.Attribute;
import com.somnus.core.hbase.factory.DefaultHBaseFactory;
import com.somnus.core.hbase.factory.HBaseFactory;
import com.somnus.core.hbase.store.base.DefaultHBaseStore;
import com.somnus.core.hbase.store.base.HBaseStore;

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
		store = new DefaultHBaseStore();
		HBaseFactory factory = new DefaultHBaseFactory();
		store.setFactory(factory);
	}

	/**
	 * Test method for
	 * {@link com.somnus.core.hbase.store.base.DefaultHBaseStore#putObject(java.lang.String, java.lang.String, java.lang.Object)}
	 * 
	 */
	@Test
	public void testPutObject() {

	}

	/**
	 * Test method for
	 * {@link com.somnus.core.hbase.store.base.DefaultHBaseStore#getAttrsByFamily(java.lang.String, java.lang.String, java.lang.String)}
	 * 
	 */
	@Test
	public void testGetNewValue() {
		Attribute attribute = store.getNewValue("aggregate",
				"Account1", "attr", "age");
		System.out.println(attribute.getName());
		System.out.println(attribute.getValue());
	}

	@Test
	public void testGetRecentValues(){
		List<Attribute> recentValues = store.getRecentValues("aggregate",
				"Account1", "attr", "name", 5);
		for (Attribute attribute : recentValues) {
			System.out.println(attribute);
		}
	}
}
