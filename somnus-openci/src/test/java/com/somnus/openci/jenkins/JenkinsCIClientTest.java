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
package com.somnus.openci.jenkins;

import java.net.MalformedURLException;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.Developer;
import com.somnus.openci.api.Project;
import com.somnus.openci.svn.SvnConfig;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JenkinsCIClientTest {

	private static Logger logger = LoggerFactory.getLogger(JenkinsCIClientTest.class);

	public static String jenkinsURL = "http://127.0.0.1:8989";
	// public static String jenkinsURL = "http://127.0.0.1:8989";
	public static String username = "admin";
	public static String apiToken = "9cfe23e07f7d5e3df3dd9d3207cfcc67";

	public static String svnUrl = "http://10.108.1.138/svn/projec";

	@Test
	public void test() throws MalformedURLException {

		Project project = getProject("uuuuu");

		JenkinsCIClient client = new JenkinsCIClient(jenkinsURL, username, apiToken);

		assert client.authenticate();

		client.setScmConfig(new SvnConfig(svnUrl));

		client.createProject(project);

		String name = "uuuuuu";
		String name1 = "xxxx1";

		client.createUserIfNecessary(project, getDeveloper(name));
		client.createUserIfNecessary(project, getDeveloper(name1));
		project.setProjectLead(name1);

		client.assignUsersToRole(project, "", getDeveloper(name));
		// client.removeProject(project);
		client.close();

	}

	public Developer getDeveloper(String name) {
		Developer developer = new Developer();
		developer.setName("中文名1");
		developer.setId(name);
		developer.setPassword("123");
		developer.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		return developer;
	}

	public Project getProject(String name) {
		Project project = new Project();
		project.setProjectName(name);
		project.setGroupId("com.c");
		project.setPhysicalPath("plpl");
		project.setDescription("中文描述");
		project.setArtifactId(name);
		return project;
	}

}
