/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.mybatis.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月29日
 */
public class User {

	private static final Logger logger = LogManager.getLogger(User.class);

	private int id;
	private String name;
	
	
	/* TODO 简单描述该方法的实现功能（可选）. 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	/**
	 * id
	 *
	 * @return  the id
	 * @since   JDK 1.6
	*/
	
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * name
	 *
	 * @return  the name
	 * @since   JDK 1.6
	*/
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
