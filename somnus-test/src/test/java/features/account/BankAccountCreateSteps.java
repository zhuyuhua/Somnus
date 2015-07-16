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

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.那么;

/**
 * TODO
 * 
 * @author:zhuyuhua
 * @date:2015年2月26日 下午3:52:13
 * @version 0.0.1
 */
public class BankAccountCreateSteps {

	private String passwd;

	@假如("^我使用密码：(\\w+)创建一个帐户$")
	public void createAccount(String password) throws Throwable {
		passwd = password;
	}

	@那么("^我会看到该密码是：无效$")
	public void isInValid() throws Throwable {
	}

	@那么("^我会看到该密码是：有效$")
	public void isValid() throws Throwable {
	}

}
