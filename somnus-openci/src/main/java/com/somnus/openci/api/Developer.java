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
package com.somnus.openci.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.exception.DeveloperValidateFailureException;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class Developer {

	private static Logger logger = LoggerFactory.getLogger(Developer.class);

	public final int PASSWORD_MIN_LENGTH = 8;

	/**
	 * 用户 ID
	 */
	private String id;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户全名
	 */
	private String fullName;

	/**
	 * 用户邮箱
	 */
	private String email;

	/**
	 * 用户角色
	 */
	private List<String> roles;

	private final static String EMAIL_REG = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

	public boolean validate() {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(email)
				|| !email.matches(EMAIL_REG) || StringUtils.length(password) < PASSWORD_MIN_LENGTH) {
			throw new DeveloperValidateFailureException("Developer is invalid");
		}
		return true;
	}
}
