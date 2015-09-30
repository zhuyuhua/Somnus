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
package com.somnus.protocol.enumeration;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public enum SerializeType {

	JSON(1), JAVABinary(2), XML(3), SomnusBinary(4);

	private final int num;

	public int getNum() {
		return num;
	}

	private SerializeType(int num) {
		this.num = num;
	}

	public static SerializeType getSerializeType(int num) {
		for (SerializeType type : SerializeType.values()) {
			if (type.getNum() == num) {
				return type;
			}
		}
		return null;
	}
}
