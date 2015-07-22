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
 * SVN的配置类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SvnConfig extends SSHConfig {

	private String svnAddress;
	private String svnUser;
	private String svnPassword;

	/**
	 * @param host
	 * @param username
	 * @param password
	 * @param storePath
	 */
	public SvnConfig(String host, String username, String password, String storePath) {
		super(host, username, password, storePath);
		// TODO Auto-generated constructor stub
	}

	/**
	 * SVN配置构造函数
	 * 
	 * @param host
	 *            Linux的SVN服务器IP
	 * @param username
	 *            Linux SSH 用户
	 * @param password
	 *            Linux SSH 密码
	 * @param storePath
	 *            svncreate命令所在的路径
	 * @param svnAddress
	 *            Apache整合SVN所在的URL
	 * @param svnUser
	 *            svn用户
	 * @param svnPassword
	 *            svn密码
	 */
	public SvnConfig(String host, String username, String password, String storePath, String svnAddress, String svnUser,
			String svnPassword) {
		super(host, username, password, storePath);
		this.svnAddress = svnAddress;
		this.svnUser = svnUser;
		this.svnPassword = svnPassword;
	}

	private static Logger logger = LoggerFactory.getLogger(SvnConfig.class);

}
