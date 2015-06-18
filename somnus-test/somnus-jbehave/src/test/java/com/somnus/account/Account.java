/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.account;

import java.util.UUID;

/**
 * TODO
 * 
 * @author:zhuyuhua
 * @date:2015年2月25日 下午5:42:36
 * @version 0.0.1
 */
public class Account {

	private int amount;

	private String id;

	private AccountType type;

	public Account(AccountType type) {
		this.id = UUID.randomUUID().toString();
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void transferTo(Account targetAccount, int amount) throws Exception {
		if (this.amount < amount) {
			throw new Exception("not enough monkey.");
		}
		this.amount = this.amount - amount;
		targetAccount.setAmount(targetAccount.getAmount() + amount);
	}

	// 记入借方（取出）
	public void debit(int amount) throws Exception {
		if (this.amount < amount) {
			throw new Exception("not Engouh monkey.");
		}
		this.amount = this.amount - amount;

	}

	// 记入贷方（存入）
	public void credit(int amount) {
		this.amount = this.amount + amount;
	}

	/**
	 * ofType
	 * 
	 * @param type
	 * @return Object
	 * @exception
	 * @since 0.0.1
	 */
	public static Account ofType(AccountType type) {
		return new Account(type);
	}

	/**
	 * withInitalBalance
	 * 
	 * @param amount2
	 * @return Account
	 * @exception
	 * @since 0.0.1
	 */
	public Account withInitalBalance(int amount) {
		this.amount = amount;
		return this;
	}

}
