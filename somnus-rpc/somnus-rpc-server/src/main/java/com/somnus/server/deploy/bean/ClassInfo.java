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

import com.somnus.server.common.ClassType;

/**
 *
 * 类信息
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ClassInfo {

	private Class<?> cls;// 类类型
	private List<MethodInfo> methodList;// 方法列表
	private ClassType classType;// 类或者接口
	private String lookUP;// 类名

	public Class<?> getCls() {
		return cls;
	}

	public List<MethodInfo> getMethodList() {
		return methodList;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public void setMethodList(List<MethodInfo> methodList) {
		this.methodList = methodList;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setLookUP(String lookUP) {
		this.lookUP = lookUP;
	}

	public String getLookUP() {
		return lookUP;
	}
}
