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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.CIClient;
import com.somnus.openci.api.Developer;
import com.somnus.openci.api.Project;

/**
 *
 * Jenkins CI 客户端
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JenkinsCIClient implements CIClient {

	private static Logger logger = LoggerFactory.getLogger(JenkinsCIClient.class);

	private SomnusScmConfig scmConfig;

	private JenkinsClient client;

	public JenkinsCIClient(String jenkinsUrl, String username, String passwordOrAPIToken) {
	        JenkinsClientFactory factory = new JenkinsClientFactory(convert(jenkinsUrl), username, passwordOrAPIToken);
	        client = factory.getJenkinsClient();
	    }

	public void setScmConfig(SomnusScmConfig scmConfig) {
		this.scmConfig = scmConfig;
	}

	@Override
	public void close() {
		client.close();
	}

	@Override
	public void createProject(Project project) {
		project.validate();

		if (existProject(project)) {
			return;
		}

		Job newJob = client.createJob(project.getProjectName(), koalaScmConfig.getScmConfig(), new ArrayList<User>());

		newJob.addFullPermissionsForUser(createByDeveloper(project.getProjectLead(), ""));
		client.updateJob(newJob);

	}

	private boolean existProject(Project project) {
		try {
			client.retrieveJob(project.getProjectName());
			return true;
		} catch (NoSuchJobException e) {
			return false;
		}
	}

	@Override
	public void removeProject(Project project) {
		try {
			Job job = client.retrieveJob(project.getProjectName());
			if (null != job) {
				client.deleteJob(job);
			}
		} catch (NoSuchJobException e) {
			return;
		}
	}

	@Override
	public void createUserIfNecessary(Project project, Developer developer) {
		client.createUser(developer.getId(), developer.getPassword(), developer.getEmail(), developer.getName());
		addUserToProjectMatrixAuthorization(developer.getId());
	}

	@Override
	public void removeUser(Project project, Developer developer) {
		try {
			User user = client.retrieveUser(developer.getId());
			if (null != user) {
				client.deleteUser(user);
			}
		} catch (NoSuchUserException e) {
			return;
		}

	}

	@Override
	public void assignUsersToRole(Project project, String role, Developer... developers) {
		assert StringUtils.isNotEmpty(project.getProjectLead());

		Job job;
		try {
			job = client.retrieveJob(project.getProjectName());
		} catch (NoSuchJobException e) {
			throw new CISClientBaseRuntimeException("jenkins.NoSuchJobException", e);
		}
		for (User user : createByDeveloper(developers)) {
			job.addPermissionsForUser(user);
			job.addNotificationRecipient(user);
		}

		client.updateJob(job);
	}

	@Override
	public boolean authenticate() {
		return client.validateServerOnEndpoint();
	}

	private URL convert(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new CISClientBaseRuntimeException("jenkins.URL.MalformedURLException", e);
		}

	}

	private List<User> createByDeveloper(Developer... developers) {
		List<User> result = new ArrayList<User>();

		for (Developer each : developers) {
			result.add(createByDeveloper(each.getId(), each.getEmail()));
		}

		return result;
	}

	public void addUserToProjectMatrixAuthorization(String developerId) {
		client.sendScriptText(MessageFormat.format(getUserAuthorizationConfig(), developerId));
	}

	private String getUserAuthorizationConfig() {

		String configFileName = "UserAuthorizationConfig.groovy";

		InputStream is = this.getClass().getResourceAsStream("/" + configFileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				builder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new CISClientBaseRuntimeException("read " + configFileName + " failure.", e);
		}

		return builder.toString();
	}


	private User createByDeveloper(String developerId, String developEmail) {
		return new UserImpl(developerId, developEmail);
	}

}
