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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;

import com.bj58.sfft.json.jsontools.parser.JSONParser;
import com.bj58.sfft.json.tools.mapper.JSONMapper;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JsonHelper {

	public static String toJsonExt(Object obj) throws Exception {
		if (obj == null) {
			return "null";
		}
		ObjectClass oc = new ObjectClass(obj);
		String result = new JSONObject(oc).toString();
		return result.substring(10, result.length() - 1);
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

	public static Object toJava(String jsonStr, Class<?> containClass, Class<?> itemClass) throws Exception {
		if (jsonStr.equalsIgnoreCase("null")) {
			return null;
		}
		Object bean = null;
		StringReader sdr = null;
		try {
			if (jsonStr.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(jsonStr);

				if ((containClass == List.class) || (containClass == ArrayList.class)) {
					bean = new ArrayList();
				} else if (containClass == Vector.class) {
					bean = new Vector();
				} else if ((containClass == Set.class) || (containClass == HashSet.class)) {
					bean = new HashSet();
				}

				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						String itemJson = jsonArray.get(i).toString();
						if ((itemClass == String.class) || (itemClass == java.util.Date.class)
								|| (itemClass == java.sql.Date.class)) {
							itemJson = "\"" + itemJson + "\"";
						}
						sdr = new StringReader(itemJson);
						JSONParser parser = new JSONParser(sdr);
						Object item = JSONMapper.toJava(parser.nextValue(), itemClass);
						((Collection) bean).add(item);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						if (sdr != null) {
							sdr.close();
						}
					}
				}
			} else {
				sdr = new StringReader(jsonStr);
				JSONParser parser = new JSONParser(sdr);
				bean = JSONMapper.toJava(parser.nextValue(), itemClass);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (sdr != null) {
				sdr.close();
			}
		}
		return bean;
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

}
