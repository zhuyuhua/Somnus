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
package org.jbehave.examples.account.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.somnus.account.Account;
import com.somnus.account.AccountType;

/**
 * TODO
 * 
 * @author:zhuyuhua
 * @date:2015年3月4日 下午2:41:12
 * @version 0.0.1
 */
public class AccountSteps {

	Account settleAccount;
	Account savingAccount;

	@Given("my $settle account has $settleamount,$saving account is $savingamount")
	public void givenMySettleAccountHas1000savingAccountIs2000(String settle,
			int settleamount, String saving, int savingamount) {
		settleAccount = Account.ofType(AccountType.valueOfCode(settle))
				.withInitalBalance(settleamount);

		savingAccount = Account.ofType(AccountType.valueOfCode(saving))
				.withInitalBalance(savingamount);

	}

	@When("I transfer $price from settle account to saving account")
	public void whenITransfer500FromSettleAccountToSavingAccount(int amount)
			throws Exception {
		settleAccount.transferTo(savingAccount, amount);
	}

	@Then("my settle account should have $amount")
	public void thenMySettleAccountShouldHave500(int amount) {
		Assert.assertEquals(amount, settleAccount.getAmount());
		// PENDING
	}

	@Then("my saving account should have $amount")
	public void thenMySavingAccountShouldHave2500(int amount) {
		Assert.assertEquals(amount, savingAccount.getAmount());
	}

}
