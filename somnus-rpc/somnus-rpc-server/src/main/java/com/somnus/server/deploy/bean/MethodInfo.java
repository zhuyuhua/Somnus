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

import java.lang.reflect.Method;
import java.util.Arrays;

import com.somnus.server.deploy.annotation.HttpRequestMapping;

/**
 * 方法类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class MethodInfo {

	private Method method;
	private ParamInfo[] paramInfoAry;
	private HttpRequestMapping httpRequestMapping;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public void setHttpRequestMapping(HttpRequestMapping httpRequestMapping) {
		this.httpRequestMapping = httpRequestMapping;
	}

	public HttpRequestMapping getHttpRequestMapping() {
		return httpRequestMapping;
	}

	public void setParamInfoAry(ParamInfo[] paramInfoAry) {
		this.paramInfoAry = paramInfoAry;
	}

	public ParamInfo[] getParamInfoAry() {
		return paramInfoAry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MethodInfo [method=" + method + ", paramInfoAry=" + Arrays.toString(paramInfoAry)
				+ ", httpRequestMapping=" + httpRequestMapping + "]";
	}

}
