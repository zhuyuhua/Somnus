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

import java.util.Map;

/**
 *
 * 类实例Bean
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SessionBean {

	private String interfaceName;
	private Map<String, String> instanceMap;
	private ClassInfo interfaceClass;

	public SessionBean() {

	}

	public SessionBean(String interfaceName, Map<String, String> instanceMap, ClassInfo interfaceClass) {
		super();
		this.interfaceName = interfaceName;
		this.instanceMap = instanceMap;
		this.interfaceClass = interfaceClass;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setInstanceMap(Map<String, String> instanceMap) {
		this.instanceMap = instanceMap;
	}

	public Map<String, String> getInstanceMap() {
		return instanceMap;
	}

	public void setInterfaceClass(ClassInfo interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public ClassInfo getInterfaceClass() {
		return interfaceClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SessionBean [interfaceName=" + interfaceName + ", instanceMap=" + instanceMap + ", interfaceClass="
				+ interfaceClass + "]";
	}

}