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
package com.somnus.openci.sonar;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SonarConnectConfig {

	private static Logger logger = LoggerFactory.getLogger(SonarConnectConfig.class);

	private String address;

	private String username;

	private String password;

	private URL addressURL;

	public SonarConnectConfig(String address, String username, String password) {
		this.address = address;
		this.username = username;
		this.password = password;
		validate();

	}

	private void validate() {
		if (StringUtils.isBlank(address)) {
			throw new CISClientBaseRuntimeException("sonar.connectAddress.null");
		}

		if (StringUtils.isBlank(username)) {
			throw new CISClientBaseRuntimeException("sonar.connectUsername.null");
		}

		if (StringUtils.isBlank(password)) {
			throw new CISClientBaseRuntimeException("sonar.connectPassword.null");
		}

		try {
			addressURL = new URL(address);
		} catch (MalformedURLException e) {
			throw new CISClientBaseRuntimeException("sonar.connectAddress.MalformedURLException");
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return addressURL.getHost();
	}

	public int getPort() {
		return addressURL.getPort();
	}

	public String getProtocol() {
		return addressURL.getProtocol();
	}

}
