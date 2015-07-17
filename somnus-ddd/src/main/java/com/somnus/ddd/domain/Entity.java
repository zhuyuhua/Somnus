/*
 * Copyright (c) 2010-2015. Aurora Framework
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
package com.somnus.ddd.domain;

import java.io.Serializable;

/**
 * 领域实体接口。所有实体类都要直接或间接实现这个接口
 * 
 * @author zhuyuhua 2015年7月15日
 * @version 0.0.1
 */
public interface Entity extends Serializable {

	/**
	 * 
	 * 获取实体的Id
	 * 
	 * @return Serializable
	 * @since 0.0.1
	 */
	Serializable getId();

	/**
	 * 
	 * 是否在数据库中已经存在
	 * 
	 * @return boolean
	 * @since 0.0.1
	 */
	boolean existed();

}
