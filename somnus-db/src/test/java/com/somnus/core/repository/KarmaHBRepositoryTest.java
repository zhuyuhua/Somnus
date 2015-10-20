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
package com.somnus.core.repository;

import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.core.domain.Account;
import com.somnus.core.hbase.factory.DefaultHBaseFactory;
import com.somnus.core.hbase.factory.HBaseFactory;
import com.somnus.core.hbase.repository.EventSourcingRepository;
import com.somnus.core.hbase.repository.Repository;
import com.somnus.core.hbase.store.EventStore;
import com.somnus.core.hbase.store.GenericEventStore;
import com.somnus.core.hbase.store.base.DefaultHBaseStore;
import com.somnus.core.hbase.store.base.HBaseStore;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class KarmaHBRepositoryTest {

	private static Logger logger = LoggerFactory
			.getLogger(KarmaHBRepositoryTest.class);

	/**
	 * Test method for
	 * {@link com.somnus.core.hbase.repository.EventSourcingRepository#persist(java.lang.String, java.lang.String, java.lang.Object)}
	 * .
	 */
	@Test
	public void testPersist() {

		HBaseStore hBaseStore = new DefaultHBaseStore();
		HBaseFactory factory = new DefaultHBaseFactory();
		hBaseStore.setFactory(factory);

		EventStore eventStore = new GenericEventStore();
		eventStore.setHbaseStore(hBaseStore);

		Repository<Account> repository = new EventSourcingRepository<Account>();
		repository.setEventStore(eventStore);

		Random random=new Random(10000);
		String base="abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM";
		for (int i = 0; i < 100; i++) {
		StringBuffer stringBuffer=new StringBuffer();
		for (int j = 0; j <8; j++) {
			int nextInt = random.nextInt(base.length());
			stringBuffer.append(base.charAt(nextInt));
		}
		
		Account account = new Account();
		account.setId(random.nextInt(10000));
		account.setAge((short) random.nextInt(125));
		account.setBalance(Double.valueOf(random.nextInt(1000)));
		account.setName(stringBuffer.toString());
		account.setCredit(Double.valueOf(random.nextInt(100)));
		repository.persist("aggregate", "1", account);
		}
	}

	/**
	 * Test method for
	 * {@link com.somnus.core.hbase.repository.EventSourcingRepository#getObject(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetObject() {

		HBaseStore hBaseStore = new DefaultHBaseStore();
		HBaseFactory factory = new DefaultHBaseFactory();
		hBaseStore.setFactory(factory);

		EventStore eventStore = new GenericEventStore();
		eventStore.setHbaseStore(hBaseStore);

		Repository<Account> repository = new EventSourcingRepository<Account>();
		repository.setEventStore(eventStore);
		
		Account object = repository.getObject("aggregate", "96",
				Account.class.getName());
		System.out.println(object);

	}

}
