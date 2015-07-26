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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.CIClient;
import com.somnus.openci.api.Developer;
import com.somnus.openci.api.Project;
import com.somnus.openci.exception.HostCannotConnectException;
import com.somnus.openci.exception.UserOrPasswordErrorException;
import com.trilead.ssh2.Connection;

/**
 *
 * svn的CISClient实现类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SvnCIClient implements CIClient {

	private static Logger logger = LoggerFactory.getLogger(SvnCIClient.class);

	private SvnConfig svnConfig;

	private Connection conn;

	/**
	 * @param configuration
	 */
	public SvnCIClient(SvnConfig svnConfig) {
		this.svnConfig = svnConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.openci.api.CIClient#createProject(com.somnus.openci.api.
	 * Project)
	 */
	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.openci.api.CIClient#removeProject(com.somnus.openci.api.
	 * Project)
	 */
	@Override
	public void removeProject(Project project) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.somnus.openci.api.CIClient#createUserIfNecessary(com.somnus.openci.
	 * api.Project, com.somnus.openci.api.Developer)
	 */
	@Override
	public void createUserIfNecessary(Project project, Developer developer) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.somnus.openci.api.CIClient#removeUser(com.somnus.openci.api.Project,
	 * com.somnus.openci.api.Developer)
	 */
	@Override
	public void removeUser(Project project, Developer developer) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.somnus.openci.api.CIClient#assignUsersToRole(com.somnus.openci.api.
	 * Project, java.lang.String, com.somnus.openci.api.Developer[])
	 */
	@Override
	public void assignUsersToRole(Project project, String role, Developer... developers) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.openci.api.CIClient#authenticate()
	 */
	@Override
	public boolean authenticate() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.openci.api.CIClient#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	/**
	 * TODO
	 */
	public boolean connect() {
		try {
			if (conn != null) {
				return true;
			}
			conn = new Connection(svnConfig.getHost());
			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(svnConfig.getUsername(), svnConfig.getPassword());

			if (!isAuthenticated) {
				conn.close();
				conn = null;
				throw new UserOrPasswordErrorException("账号或密码错误！");
			}
			return true;
		} catch (IOException e) {
			conn.close();
			conn = null;
			throw new HostCannotConnectException("无法连接到主机！");
		}
	}

}
