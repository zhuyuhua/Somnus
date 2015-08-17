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

package com.somnus.example.spring.aop.business;

/**
 * @author zhuyuhua
 * @version 1.0
 */
public interface IBaseBusiness {
	/**
	 * 用作代理的切入点方法
	 * 
	 * @param obj
	 * @return String
	 */
	public String delete(String obj);

	/**
	 * 这方法不被切面切
	 * 
	 * @param obj
	 * @return String
	 */
	public String add(String obj);

	/**
	 * 这方法切不切呢？可以设置
	 * 
	 * @param obj
	 * @return String
	 */
	public String modify(String obj);
}
