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
package com.somnus.bdd.features.account;

import org.junit.Assert;

import com.somnus.bdd.account.Account;
import com.somnus.bdd.account.AccountType;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @date:2015年2月27日 下午5:33:24
 * @version 0.0.1
 */
public class BankAccountTest {

	// @Test
	public void should_transfer_settle_account_to_a_saving_account()
			throws Exception {
		Account settleAccount = Account.ofType(AccountType.SETTLE)
				.withInitalBalance(1000);
		Account savingAccount = Account.ofType(AccountType.SAVING)
				.withInitalBalance(2000);
		settleAccount.transferTo(savingAccount, 500);
		Assert.assertEquals(500, settleAccount.getAmount());
		Assert.assertEquals(2500, savingAccount.getAmount());
	}
}
