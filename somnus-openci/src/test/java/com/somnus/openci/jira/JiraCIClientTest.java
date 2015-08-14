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
package com.somnus.openci.jira;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.Developer;
import com.somnus.openci.api.Project;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JiraCIClientTest {

	private static Logger logger = LoggerFactory.getLogger(JiraCIClientTest.class);

	private String username = "admin";

	private String url = "http://10.108.1.92:8082/";

	private JiraCIClient client = new JiraCIClient(url, username, "admin");

	@Before
	public void setUp() throws Exception {
		assert client.authenticate();

	}

	// @After
	public void tearDown() throws Exception {

		client.removeProject(getProject());
		assert!client.isProjectExist(getProject());

	}

	@Test
	public void testName() throws Exception {

		// 注意project key的问题
		client.createProject(getProject());

		assert client.isProjectExist(getProject());

		for (Developer developer : createDevelopers(10)) {
			client.createUserIfNecessary(null, developer);
			client.assignUsersToRole(getProject(), "", developer);

			JiraCISClient developClient = new JiraCISClient(url, developer.getId(), developer.getPassword());
			assert developClient.authenticate();
			assert client.isUserAtProjectDevelopRole(getProject(), developer);
		}

		for (Developer developer : createDevelopers(10)) {
			client.createUserIfNecessary(null, developer);
			client.assignUsersToRole(getProject(), "", developer);
		}

	}

	private Project getProject() {
		Project project = new Project();
		project.setArtifactId("dddlib");
		project.setGroupId("org.dayatang");
		project.setProjectName("dddlib");
		project.setProjectLead("projectLeader");
		project.setDescription("description");
		project.setProjectLead(username);
		return project;
	}

	private List<Developer> createDevelopers(int count) {
		List<Developer> developers = new ArrayList<Developer>();
		for (int i = count; i > 0; i--) {
			developers.add(getDeveloper("usernamexx" + i));
		}
		return developers;

	}

	private Developer getDeveloper(String id) {
		Developer developer = new Developer();
		developer.setName("中文全名");
		developer.setPassword("12345678");
		developer.setEmail(id + "kkkkkkk@1dfdf.com");
		developer.setId(id);
		developer.setFullName("中文全名");
		return developer;
	}

}
