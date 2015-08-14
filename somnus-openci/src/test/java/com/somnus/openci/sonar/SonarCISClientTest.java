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

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.Project;
import com.sun.net.httpserver.HttpServer;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SonarCISClientTest {

	private static Logger logger = LoggerFactory.getLogger(SonarCISClientTest.class);

	public static final String address2 = "http://localhost:12306";
	public static final String username = "admin";
	public static final String password = "admin";

	private HttpServer server = httpserver(12306);

	private SonarCISClient sonarCISClient;

	@Before
	public void setUp() throws Exception {
		server.get(by(uri("/api/authentication/validate"))).response(with("{\"validate\":true}"), status(200));
		running(server, new Runnable() {
			@Override
			public void run() throws IOException {
				sonarCISClient = new SonarCISClient(new SonarConnectConfig(address2, username, password));
				assert sonarCISClient.authenticate();
			}
		});

	}

	@Test
	public void test() {
	}

	@Ignore
	@Test
	public void testCreateProject() throws Exception {
		server.post(and(by(uri("/api/projects/create")), eq(query("key"), SonarCISClient.getKeyOf(getProject())),
				eq(query("name"), getProject().getProjectName())))
				.response(with("{\n" + "    \"id\":\"12\",\n" + "    \"k\":\"" + SonarCISClient.getKeyOf(getProject())
						+ "\",\n" + "    \"nm\":\"yyy\",\n" + "    \"sc\":\"PRJ\",\n" + "    \"qu\":\"TRK\"\n" + "}"),
						status(200));

		running(server, new Runnable() {
			@Override
			public void run() throws IOException {
				sonarCISClient.createProject(getProject());
			}
		});

	}

	private Project getProject() {
		Project project = new Project();
		project.setProjectLead("lead");
		project.setArtifactId("artifact");
		project.setDescription("xxx");
		project.setGroupId("group");
		project.setProjectName("yyy");
		project.setPhysicalPath("physical");
		return project;
	}
}
