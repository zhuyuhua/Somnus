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
package com.somnus.protocol.enumeration;

import com.somnus.protocol.entity.ExceptionProtocol;
import com.somnus.protocol.entity.RequestProtocol;
import com.somnus.protocol.entity.ResponseProtocol;

/**
 * 协议枚举类型
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public enum SDPType {

	Response(1), Request(2), Exception(3);

	private final int num;

	public int getNum() {
		return num;
	}

	private SDPType(int num) {
		this.num = num;
	}

	public static SDPType getSDPType(int num) throws java.lang.Exception {
		for (SDPType type : SDPType.values()) {
			if (type.getNum() == num) {
				return type;
			}
		}
		throw new Exception("末知的SDP:" + num);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static SDPType getSDPType(Class<?> clazz) throws Exception {
		if (clazz == RequestProtocol.class) {
			return SDPType.Request;
		} else if (clazz == ResponseProtocol.class) {
			return SDPType.Response;
		} else if (clazz == ExceptionProtocol.class) {
			return SDPType.Exception;
		}
		throw new Exception("末知的SDP:" + clazz.getName());
	}

	/**
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getSDPClass(SDPType type) throws Exception {
		if (type == SDPType.Request) {
			return RequestProtocol.class;
		} else if (type == SDPType.Response) {
			return ResponseProtocol.class;
		} else if (type == SDPType.Exception) {
			return ExceptionProtocol.class;
		}
		throw new Exception("末知的SDP:" + type);
	}

	public static SDPType getSDPType(Object obj) {
		if (obj instanceof RequestProtocol) {
			return SDPType.Request;
		} else if (obj instanceof ResponseProtocol) {
			return SDPType.Response;
		} else {
			return SDPType.Exception;
		}
	}
}
