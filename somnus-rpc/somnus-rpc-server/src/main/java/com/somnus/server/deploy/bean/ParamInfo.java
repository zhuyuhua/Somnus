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

import java.lang.reflect.Type;

import com.somnus.server.deploy.annotation.HttpPathParameter;

/**
 *
 * 参数类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ParamInfo {
	private int index;
	private Class<?> cls;
	private Type type;
	private String name;
	private String mapping;
	private HttpPathParameter httpPathParameter;

	public ParamInfo() {

	}

	public ParamInfo(int index, Class<?> cls, Type type, String name, String mapping,
			HttpPathParameter httpPathParameter) {
		super();
		this.index = index;
		this.cls = cls;
		this.type = type;
		this.name = name;
		this.mapping = mapping;
		this.httpPathParameter = httpPathParameter;
	}

	public int getIndex() {
		return index;
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHttpPathParameter(HttpPathParameter httpPathParameter) {
		this.httpPathParameter = httpPathParameter;
	}

	public HttpPathParameter getHttpPathParameter() {
		return httpPathParameter;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getMapping() {
		return mapping;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public Class<?> getCls() {
		return cls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParamInfo [index=" + index + ", cls=" + cls + ", type=" + type + ", name=" + name + ", mapping="
				+ mapping + ", httpPathParameter=" + httpPathParameter + "]";
	}

}
