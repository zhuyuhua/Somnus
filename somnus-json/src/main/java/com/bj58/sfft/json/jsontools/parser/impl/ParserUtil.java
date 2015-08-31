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
package com.bj58.sfft.json.jsontools.parser.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ParserUtil {

	private static Logger logger = LoggerFactory.getLogger(ParserUtil.class);

	public static String hexToChar(String i, String j, String k, String l) {
		return Character.toString((char) Integer.parseInt(i + j + k + l, 16));
	}

	public static String render(String value, boolean pretty, String indent) {
		StringBuilder lBuf = new StringBuilder();
		if (pretty) {
			lBuf.append(indent);
		}
		lBuf.append("\"");
		if (value.indexOf("\\/Date(") >= 0) {
			for (int i = 0; i < value.length(); i++) {
				char lChar = value.charAt(i);
				lBuf.append(lChar);
			}
		} else {
			for (int i = 0; i < value.length(); i++) {
				char lChar = value.charAt(i);
				if (lChar == '\n') {
					lBuf.append("\\n");
				} else if (lChar == '\r') {
					lBuf.append("\\r");
				} else if (lChar == '\t') {
					lBuf.append("\\t");
				} else if (lChar == '\b') {
					lBuf.append("\\b");
				} else if (lChar == '\f') {
					lBuf.append("\\f");
				} else if (lChar == '"') {
					lBuf.append("\\\"");
				} else if (lChar == '\\') {
					lBuf.append("\\\\");
				} else {
					lBuf.append(lChar);
				}
			}
		}
		lBuf.append("\"");
		return lBuf.toString();
	}
}
