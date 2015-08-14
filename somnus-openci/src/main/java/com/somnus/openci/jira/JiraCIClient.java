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

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.api.CIClient;
import com.somnus.openci.api.Developer;
import com.somnus.openci.api.Project;
import com.somnus.openci.exception.CIClientBaseRuntimeException;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JiraCIClient implements CIClient {

	private static Logger logger = LoggerFactory.getLogger(JiraCIClient.class);

	/**
	 * jira soap服务接口
	 */
	private JiraService jiraService;

	private String jiraServerAddress;

	private String adminUserName;

	private String adminPassword;

	public JiraCIClient(String jiraServerAddress, String adminUserName, String adminPassword) {
        this.jiraServerAddress = jiraServerAddress;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

	@Override
	public void close() {
		// do nothing
	}

	@Override
	public void createProject(Project project) {
		project.validate();

		if (!jiraService.isUserExist(project.getProjectLead())) {
			throw new CIClientBaseRuntimeException("jira.projectLeadNotExists");
		}

		if (jiraService.isProjectExist(project)) {
			return;
		}

		try {
			jiraService.createProject(project, jiraServerAddress);
		} catch (RemotePermissionException e) {
			throw new CIClientBaseRuntimeException(e.getMessage(), e);
		} catch (RemoteValidationException e) {
			throw new CIClientBaseRuntimeException(e.getMessage(), e);
		} catch (RemoteAuthenticationException e) {
			throw new CIClientBaseRuntimeException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new CIClientBaseRuntimeException(e.getMessage(), e);
		} catch (java.rmi.RemoteException e) {
			throw new CIClientBaseRuntimeException(e.getMessage(), e);
		}
	}

	public boolean isProjectExist(Project project) {
		return jiraService.isProjectExist(project);
	}

	@Override
	public void createUserIfNecessary(Project project, Developer developer) {
		developer.validate();
		// 用户存在，则不创建，忽略
		if (isDeveloperExist(developer)) {
			return;
		}
		try {
			jiraService.createUser(developer);
		} catch (RemotePermissionException e) {
			throw new CISClientBaseRuntimeException("jira.remotePermissionException", e);
		} catch (RemoteValidationException e) {
			throw new CISClientBaseRuntimeException("jira.remoteValidationException", e);
		} catch (RemoteAuthenticationException e) {
			throw new CISClientBaseRuntimeException("jira.remoteAuthenticationException", e);
		} catch (java.rmi.RemoteException e) {
			throw new CISClientBaseRuntimeException("jira.remoteException", e);
		}
	}

	@Override
	public void removeUser(Project project, Developer developer) {
		if (!jiraService.isUserExist(developer.getId())) {
			return;
		}

		try {
			jiraService.deleteUser(developer.getId());
		} catch (RemotePermissionException e) {
			throw new CISClientBaseRuntimeException("jira.remotePermissionException", e);
		} catch (Exception e) {
			throw new CISClientBaseRuntimeException("jira.remoteException", e);
		}
	}

	@Override
	public boolean authenticate() {
		JiraSoapServiceServiceLocator jiraSoapServiceLocator = new JiraSoapServiceServiceLocator();
		jiraSoapServiceLocator
				.setJirasoapserviceV2EndpointAddress(jiraServerAddress + "/rpc/soap/jirasoapservice-v2?wsdl");
		String token = null;
		try {
			JiraSoapService jiraSoapService = jiraSoapServiceLocator.getJirasoapserviceV2();
			token = jiraSoapService.login(adminUserName, adminPassword);
			jiraService = new KoalaJiraService(token, jiraSoapService);
		} catch (ServiceException e) {
			throw new CISClientBaseRuntimeException("jira.authenticationServiceException", e);
		} catch (RemoteAuthenticationException e) {
			// Invalid username or password
			return false;
		} catch (RemoteException e) {
			return false;
		} catch (java.rmi.RemoteException e) {
			return false;
		}
		return token != null;
	}

	public boolean isUserAtProjectDevelopRole(Project project, Developer developer) {
		try {
			return jiraService.isUserAtProjectDevelopRole(KoalaJiraService.getProjectKey(project), developer.getId());
		} catch (java.rmi.RemoteException e) {
			throw new CISClientBaseRuntimeException("isUserAtProjectDevelopRole", e);
		}

	}

	@Override
	public void assignUsersToRole(Project project, String role, Developer... developers) {
		for (Developer each : developers) {
			checkProjectRoleUserAllExist(project, each.getId());
			try {
				if (jiraService.isUserAtProjectDevelopRole(project, each)) {
					continue;
				}

				jiraService.addActorsToProjectRole(KoalaJiraService.getProjectKey(project), each.getId(),
						KoalaJiraService.DEFAULT_PROJECT_ROLE_DEVELOP);
			} catch (java.rmi.RemoteException e) {
				throw new CISClientBaseRuntimeException("jira.assignUsersToRoleRemoteException", e);
			}
		}

	}

	/**
	 * 检查项目、用户、角色是否都存在
	 *
	 * @return
	 */
	private void checkProjectRoleUserAllExist(Project project, String userName) {
		if (!jiraService.isProjectExist(project)) {
			throw new CISClientBaseRuntimeException("jira.projectNotExists");
		}
		if (!jiraService.isUserExist(userName)) {
			throw new CISClientBaseRuntimeException("jira.userNotExists");
		}
	}

	public boolean isRoleExist(String roleName) {
		return jiraService.isRoleExist(roleName);
	}

	/**
	 * 仅供单元测试调用
	 */
	@Override
	public void removeProject(Project project) {
		try {
			if (!jiraService.isProjectExist(project)) {
				return;
			}
			jiraService.deleteProject(project);
		} catch (RemotePermissionException e) {
			throw new CISClientBaseRuntimeException("jira.remotePermissionException");
		} catch (java.rmi.RemoteException e) {
			throw new CISClientBaseRuntimeException("jira.remoteException");
		}
	}

	public boolean isDeveloperExist(Developer developer) {
		return jiraService.isUserExist(developer.getId());
	}
}
