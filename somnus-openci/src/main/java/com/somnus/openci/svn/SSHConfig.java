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
package com.somnus.openci.svn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SSHConfig {

	private static Logger logger = LoggerFactory.getLogger(SSHConfig.class);

	private String host;

	private String username;

	private String password;

	private String storePath;

	/**
	 * host
	 *
	 * @return host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * username
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * password
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * storePath
	 *
	 * @return storePath
	 */
	public String getStorePath() {
		return storePath;
	}

	/**
	 * @param storePath
	 */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	/**
	 * @param host
	 * @param username
	 * @param password
	 * @param storePath
	 */
	public SSHConfig(String host, String username, String password, String storePath) {
		super();
		this.host = host;
		this.username = username;
		this.password = password;
		this.storePath = storePath;
	}

}
