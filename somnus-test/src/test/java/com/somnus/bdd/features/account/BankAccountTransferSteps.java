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

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;

public class BankAccountTransferSteps {

	private Account settleAccount;

	private Account savingAccount;

	@假如("^我有一个结算帐户，余额：(\\d+)")
	public void 我有一个结算帐户_余额(int amount) throws Throwable {
		settleAccount = Account.ofType(AccountType.SETTLE).withInitalBalance(
				amount);
	}

	@假如("^我有一个储蓄帐户，余额：(\\d+)$")
	public void 我有一个储蓄帐户_余额(int arg1) throws Throwable {
		savingAccount = Account.ofType(AccountType.SAVING).withInitalBalance(
				arg1);
	}

	@当("^我从结算帐户转(\\d+)块到我的储蓄帐户$")
	public void 我从结算帐户转_块到我的储蓄帐户储蓄帐户(int arg1) throws Throwable {
		settleAccount.transferTo(savingAccount, arg1);
	}

	@那么("^我的结算帐户余额是：(\\d+)$")
	public void 我的结算帐户余额是(int arg1) throws Throwable {
		Assert.assertEquals(arg1, settleAccount.getAmount());
	}

	@那么("^我的储蓄帐户余额是：(\\d+)$")
	public void 我的储蓄帐户余额是(int arg1) throws Throwable {
		Assert.assertEquals(arg1, savingAccount.getAmount());
	}

}
