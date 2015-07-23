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

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public interface CIClient {


	/**
	 * 创建一个项目
	 *
	 * @param project
	 */
    void createProject(Project project);

    /**
	 * 删除项目
	 *
	 * @param project
	 */
    void removeProject(Project project);

    /**
	 * 创建一个用户，如果此用户不存在的话
	 * 
	 * @param project
	 * @param developer
	 */
    void createUserIfNecessary(Project project, Developer developer);

	/**
	 * 删除用户
	 * 
	 * @param project
	 * @param developer
	 */
    void removeUser(Project project, Developer developer);


    /**
	 * 关联用户和角色
	 */
    void assignUsersToRole(Project project, String role, Developer... developers);


    /**
     * 认证
     *
     * @return
     */
    boolean authenticate();


    /**
     * 关闭与具体工具的连接
     */
    void close();
}
