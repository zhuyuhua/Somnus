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
package com.somnus.openci.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.exception.ProjectValidateFailureException;

/**
 *
 * 项目
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class Project {

	private static Logger logger = LoggerFactory.getLogger(Project.class);

	private String artifactId;

    private String groupId;

    private String projectName;

    /**
     * 项目负责人
     */
    private String projectLead;

    /**
     * 在硬盘中的物理路径
     */
    private String physicalPath;

    private String description;

    private List<Developer> developers = new ArrayList<Developer>();
    
    public boolean validate() {
        if (projectName.length() < 2) {
            throw new ProjectValidateFailureException("project.validateFailure");
        }

        if (StringUtils.isBlank(groupId)) {
            throw new ProjectValidateFailureException("project.groupId.null");
        }

        if (StringUtils.isBlank(artifactId)) {
            throw new ProjectValidateFailureException("project.artifactId.null");

        }

        return true;
    }

	/**
	 * artifactId
	 *
	 * @return artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @param artifactId
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * groupId
	 *
	 * @return groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * projectName
	 *
	 * @return projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * projectLead
	 *
	 * @return projectLead
	 */
	public String getProjectLead() {
		return projectLead;
	}

	/**
	 * @param projectLead
	 */
	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}

	/**
	 * physicalPath
	 *
	 * @return physicalPath
	 */
	public String getPhysicalPath() {
		return physicalPath;
	}

	/**
	 * @param physicalPath
	 */
	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	/**
	 * description
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * developers
	 *
	 * @return developers
	 */
	public List<Developer> getDevelopers() {
		return developers;
	}

	/**
	 * @param developers
	 */
	public void setDevelopers(List<Developer> developers) {
		this.developers = developers;
	}

}
