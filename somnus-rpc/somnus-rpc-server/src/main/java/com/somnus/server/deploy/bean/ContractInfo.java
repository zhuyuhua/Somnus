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
package com.somnus.server.deploy.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ContractInfo {

	private static Logger logger = LoggerFactory.getLogger(ContractInfo.class);

	private List<SessionBean> sessionBeanList;

	public ContractInfo() {

	}

	public ContractInfo(List<SessionBean> sessionBeanList) {
		super();
		this.sessionBeanList = sessionBeanList;
	}

	public void setSessionBeanList(List<SessionBean> sessionBeanList) {
		this.sessionBeanList = sessionBeanList;
	}

	public List<SessionBean> getSessionBeanList() {
		return sessionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContractInfo [sessionBeanList=" + sessionBeanList + "]";
	}

}
