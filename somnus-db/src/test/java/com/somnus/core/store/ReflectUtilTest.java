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

import com.somnus.core.domain.Account;
import com.somnus.core.hbase.domain.Attribute;
import com.somnus.core.hbase.store.ReflectUtil;

/**
 * TODO
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class ReflectUtilTest {


	/**
	 * Test method for {@link com.somnus.core.hbase.store.ReflectUtil#getAttributes(java.lang.Object)}.
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
