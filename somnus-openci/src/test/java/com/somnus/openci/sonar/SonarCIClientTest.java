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

import java.util.ArrayList;

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
public class SonarCIClientTest {

	private static Logger logger = LoggerFactory.getLogger(SonarCIClientTest.class);

	public static final String address2 = "http://127.0.0.1:9000";
	public static final String username = "admin";
	public static final String password = "admin";

	private static Project project = new Project();

	static {
		project.setProjectName("plplhtt");
		project.setArtifactId("plplhttt");
		project.setGroupId("com.grou3");
		project.setDescription("description");
		project.setProjectLead("xxx");
	}

	@Test
	public void testAuthenticate() {
		SonarCISClient sonarCISClient = new SonarCISClient(new SonarConnectConfig(address2, username, password));

		assert sonarCISClient.authenticate();

		for (Developer developer : createDevelopers()) {
			sonarCISClient.createUserIfNecessary(null, developer);
		}

		for (Developer developer : createDevelopers()) {
			sonarCISClient.createUserIfNecessary(null, developer);
		}

		Developer developer = new Developer();
		developer.setId("plplhtt");
		developer.setName("plpttg");
		developer.setEmail("xxx@d1xx.com");
		developer.setPassword("xxxxx");
		developer.setFullName("fullname");

		sonarCISClient.createUserIfNecessary(null, developer);

		sonarCISClient.createProject(project);

		assert sonarCISClient.existsUser(developer.getId());

		SonarCISClient developerClient = new SonarCISClient(
				new SonarConnectConfig(address2, developer.getId(), developer.getPassword()));
		assert developerClient.authenticate();
		developerClient.close();
		assert sonarCISClient.isActivated(developer.getId());

		sonarCISClient.assignUsersToRole(project, "", developer);

		// sonarCISClient.removeUser(project, developer);
		// sonarCISClient.removeProject(project);
		// assert !sonarCISClient.isActivated(developer.getId());

		sonarCISClient.close();

	}

	public List<Developer> createDevelopers() {
		List<Developer> results = new ArrayList<Developer>();

		for (int i = 0; i < 30; i++) {
			Developer developer1 = new Developer();
			developer1.setId("plplhf111111f" + i);
			developer1.setName("plplff" + i);
			developer1.setEmail(i + "xxx@d1xx.com");
			developer1.setPassword("xxxxx");
			developer1.setFullName(i + "fullname");
			results.add(developer1);
		}

		return results;
	}

	@Test(expected = CISClientBaseRuntimeException.class)
	public void testAssignRole() {
		SonarCISClient sonarCISClient1 = new SonarCISClient(new SonarConnectConfig(address2, username, password));
		assert sonarCISClient1.authenticate();
		Developer developer1 = new Developer();
		developer1.setId("plplhf111111f");
		developer1.setName("plplff");
		developer1.setEmail("xxx@d1xx.com");
		developer1.setPassword("xxxxx");
		developer1.setFullName("fullname");
		sonarCISClient1.assignUsersToRole(project, "role", developer1);
	}

}
