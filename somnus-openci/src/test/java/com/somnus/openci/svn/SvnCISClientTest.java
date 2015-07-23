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

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SvnCISClientTest {

	private static Logger logger = LoggerFactory.getLogger(SvnCISClientTest.class);

	private SvnConfig configuration;

	private String host = "127.0.0.1";
	private String port = "444";

	private String username = "Administrator";
	private String password = "zhuyuhua";

	private String svnAddress = "https://127.0.0.1:444/!/#somnus";
	private String storePath = "E:/svn/svn/somnus";
	private String svnUser = "zhuyuhua";
	private String svnPassword = "zhuyuhua";

	@Before
	public void setUp() throws Exception {

		configuration = new SvnConfig("10.108.1.92", "apache", "opencis147", "/home/svn/",
				"http://10.108.1.92/svn/myproject", "Koala", "Koala");

	}
}
