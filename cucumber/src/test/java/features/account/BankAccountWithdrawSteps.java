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
package features.account;

import org.junit.Assert;

import com.somnus.account.Account;
import com.somnus.account.AccountType;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;

public class BankAccountWithdrawSteps {

	private Account savingAccount;

	private int withdrawal;// 取款金额

	@假如("^我有一个储蓄帐户，余额是(\\d+)$")
	public void 我有一个储蓄帐户_余额(int arg1) throws Throwable {
		savingAccount = Account.ofType(AccountType.SAVING).withInitalBalance(
				arg1);
	}

	@当("^我往储蓄账户存入(\\d+)$")
	public void 我往储蓄账户存入(int amount) throws Throwable {
		savingAccount.credit(amount);
	}

	@当("^我从储蓄帐户取现(\\d+)$")
	public void 我从储蓄帐户取出(int amount) throws Throwable {
		withdrawal = amount;
		savingAccount.debit(amount);
	}

	@那么("^我得到(\\d+)现金$")
	public void receive(int amount) throws Throwable {
		Assert.assertEquals(amount, withdrawal);
	}

	@那么("^我的储蓄帐户剩下余额是(\\d+)$")
	public void 我的储蓄帐户剩下余额是(int arg1) throws Throwable {
		Assert.assertEquals(arg1, savingAccount.getAmount());
	}

}
