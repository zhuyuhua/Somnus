/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.repository;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pingan.karma.domain.Account;
import com.pingan.karma.factory.HBaseFactory;
import com.pingan.karma.factory.KarmaHBaseFactory;
import com.pingan.karma.store.EventStore;
import com.pingan.karma.store.GenericEventStore;
import com.pingan.karma.store.base.HBaseStore;
import com.pingan.karma.store.base.KarmaHBaseStore;

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
	 * {@link com.pingan.karma.repository.EventSourcingRepository#persist(java.lang.String, java.lang.String, java.lang.Object)}
	 * .
	 */
	@Test
	public void testPersist() {

		HBaseStore hBaseStore = new KarmaHBaseStore();
		HBaseFactory factory = new KarmaHBaseFactory();
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
	 * {@link com.pingan.karma.repository.EventSourcingRepository#getObject(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetObject() {

		HBaseStore hBaseStore = new KarmaHBaseStore();
		HBaseFactory factory = new KarmaHBaseFactory();
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
