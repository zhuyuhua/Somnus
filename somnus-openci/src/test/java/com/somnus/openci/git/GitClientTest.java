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
package com.somnus.openci.git;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class GitClientTest {

	private static Logger logger = LoggerFactory.getLogger(GitClientTest.class);

	// 本地git项目
	private static final String REPOSITORY_PATH = "E:/svn/git/test";

	// github的地址
	private String REMOTE_REPOSITORY_URL = "https://github.com/zhuyuhua/test.git";

	private GitClient client;

	private String username = "zhuyuhua";
	private String password = "password";
	private String email = "zhu.yuhua@qq.com";

	@Before
	public void setUp() throws Exception {

		client = new GitClient(username, password, email, REPOSITORY_PATH);

	}

	@Test
	public void testGitClientAll() throws Exception {

		assert!GitClient.isInited(REPOSITORY_PATH);

		if (!GitClient.isInited(REPOSITORY_PATH)) {
			GitClient.init(REPOSITORY_PATH, REMOTE_REPOSITORY_URL);
			assert GitClient.isInited(REPOSITORY_PATH);
		}

		Git git = Git.open(new File(REPOSITORY_PATH));

		assert!git.getRepository().isBare();

		client.add(".");

		client.commit("init");

		client.pushRepositoryToRemote(REMOTE_REPOSITORY_URL);

		logger.debug("gitclient success.");

	}

}
