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
package com.somnus.protocol.utility;

import java.io.Serializable;

import com.somnus.protocol.server.annotation.Member;
import com.somnus.protocol.server.entity.Out;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public class KeyValuePair implements Serializable {

	private static final long serialVersionUID = -5372970719983463898L;

	@Member(name = "name")
	private String key;

	@Member
	private Object value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		if (value instanceof Out) {
			this.value = ((Out<?>) value).getOutPara();
		} else {
			this.value = value;
		}
	}

	public KeyValuePair() {
	}

	public KeyValuePair(String key, Object value) {
		this.setKey(key);
		this.setValue(value);
	}
}
