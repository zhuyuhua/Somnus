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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.Developer;
import com.somnus.openci.api.Project;
import com.somnus.openci.exception.HostCannotConnectException;

/**
 *
 * SVN测试类 需要依赖SVN服务器
 * 
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SvnCIClientTest {

	private static Logger logger = LoggerFactory.getLogger(SvnCIClientTest.class);

	private SvnConfig configuration;

	private SvnCIClient instance;
	private Project project;
	private Developer developer;

	private String host = "127.0.0.1";
	private String port = "444";

	private String username = "Administrator";
	private String password = "zhuyuhua";

	private String svnAddress = "https://127.0.0.1:444/!/#somnus";
	private String storePath = "E:/svn/svn/somnus";
	private String svnUser = "zhuyuhua";
	private String svnPassword = "zhuyuhua";

	private String projectName = "somnus";

	@Before
	public void setUp() throws Exception {

		configuration = new SvnConfig(host, username, "opencis147", storePath, svnAddress, svnUser, svnPassword);

		// 初始化项目
		project = new Project();
		project.setProjectName(projectName);
		project.setPhysicalPath(storePath);

		// 初始化项目开发者
		developer = new Developer();
		developer.setId("zyh01");
		developer.setName("zyh_dev");
		developer.setPassword("zyh_dev_passwd");
	}

	@Test(expected = HostCannotConnectException.class)
	public void testHostCannotConnect() {
		configuration.setHost(host);
		instance = new SvnCIClient(configuration);
		instance.connect();
	}

	@After
	public void tearDown() throws Exception {
		configuration = null;
		instance = null;
		project = null;
		developer = null;
	}
}
