/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.store;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pingan.karma.domain.Account;
import com.pingan.karma.domain.Attribute;

/**
 * TODO
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class ReflectUtilTest {


	/**
	 * Test method for {@link com.pingan.karma.store.ReflectUtil#getAttributes(java.lang.Object)}.
	 */
	@Test
	public void testGetAttributes() {
	  Account account=new Account();
	  account.setId(1);
	  account.setAge((short)23);
	  account.setBalance(345667.23);
	  account.setName("zdx");
	  account.setCredit(100.2);
	  
	  List<Attribute> attributes = ReflectUtil.getAttributes(account);
	  for (Attribute attribute : attributes) {
		System.out.println(attribute);
	}
	}

}
