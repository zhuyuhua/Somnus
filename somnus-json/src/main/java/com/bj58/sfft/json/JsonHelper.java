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
package com.bj58.sfft.json;

import java.io.StringReader;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JsonHelper {

	private static Logger logger = LoggerFactory.getLogger(JsonHelper.class);

	public static String toJsonExt(Object obj) throws Exception {
		if (obj == null) {
			return "null";
		}
		ObjectClass oc = new ObjectClass(obj);
		String result = new JSONObject(oc).toString();
		return result.substring(10, result.length() - 1);
	}

	public static class ObjectClass {
		private Object result;

		public void setResult(Object result) {
			this.result = result;
		}

		public Object getResult() {
			return this.result;
		}

		public ObjectClass(Object result) {
			setResult(result);
		}
	}

	public static Object toJava(String jsonStr, Class<?> clazz) throws Exception {
		if (jsonStr.equalsIgnoreCase("null")) {
			return null;
		}
		Object bean = null;
		StringReader sdr = null;
		try {
			sdr = new StringReader(jsonStr);
			JSONParser parser = new JSONParser(sdr);
			bean = JSONMapper.toJava(parser.nextValue(), clazz);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sdr != null) {
				sdr.close();
			}
		}
		return bean;
	}

}
