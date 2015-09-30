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
package com.somnus.protocol.entity;

import java.util.List;

import com.somnus.protocol.server.annotation.Member;
import com.somnus.protocol.utility.KeyValuePair;

/**
 * 请求协议类
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public class RequestProtocol {

	@Member
	private String lookup;// 接口实现类
	@Member
	private String methodName;// 方法名
	@Member
	private List<KeyValuePair> paraKVList;// 参数键值对

	public RequestProtocol() {
	}

	public RequestProtocol(String lookup, String methodName, List<KeyValuePair> paraKVList) {
		this.lookup = lookup;
		this.methodName = methodName;
		this.paraKVList = paraKVList;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<KeyValuePair> getParaKVList() {
		return paraKVList;
	}

	public void setParaKVList(List<KeyValuePair> paraKVList) {
		this.paraKVList = paraKVList;
	}
}
