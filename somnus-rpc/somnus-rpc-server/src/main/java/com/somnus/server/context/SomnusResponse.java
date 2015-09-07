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
package com.somnus.server.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SomnusResponse {

	private static Logger logger = LoggerFactory.getLogger(SomnusResponse.class);

	private Object returnValue;

	// private List<Out<?>> outParaList;

	private byte[] responseBuffer;

	/**
	 * returnValue
	 *
	 * @return returnValue
	 */
	public Object getReturnValue() {
		return returnValue;
	}

	/**
	 * @param returnValue
	 */
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * responseBuffer
	 *
	 * @return responseBuffer
	 */
	public byte[] getResponseBuffer() {
		return responseBuffer;
	}

	/**
	 * @param responseBuffer
	 */
	public void setResponseBuffer(byte[] responseBuffer) {
		this.responseBuffer = responseBuffer;
	}

}
