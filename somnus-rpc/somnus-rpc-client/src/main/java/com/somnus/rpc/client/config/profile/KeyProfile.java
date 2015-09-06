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
package com.somnus.rpc.client.config.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

/**
 *
 * 授权文件
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class KeyProfile {

	private static Logger logger = LoggerFactory.getLogger(KeyProfile.class);

	/**
	 * 授权文件
	 */
	private String info;

	public KeyProfile(Node node) {
		if (node != null) {
			Node infoNode = node.getAttributes().getNamedItem("info");
			if (infoNode != null) {
				this.info = infoNode.getNodeValue();
			}
		}
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyProfile [info=" + info + "]";
	}

}
