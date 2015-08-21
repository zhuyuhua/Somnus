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
package com.somnus.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class PropertiesHelper {

	private static Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);

	public static Properties load(String path) {
		Properties properties = new Properties();
		try {

			properties.load(new FileInputStream(path));
			return properties;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * TODO
	 * 
	 * @param fServiceConfig
	 * @return
	 */
	public static Properties load(File file) {
		Properties properties = new Properties();
		try {

			properties.load(new FileInputStream(file));
			return properties;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static void copy(Properties source, Properties target) {
		if (source == null) {
			return;
		}

		if (target == null) {
			target = new Properties();
		}

		Object[] objs = source.keySet().toArray();
		for (Object key : objs) {
			target.put(key, source.get(key));
		}
	}

	/**
	 * TODO
	 * 
	 * @param properties
	 * @param property
	 */
	public static void prop2Map(Properties properties, Map<Object, Object> propMap) {
		if (properties == null) {
			return;
		}

		if (propMap == null) {
			return;
		}

		Set<Entry<Object, Object>> propertySet = properties.entrySet();
		for (Object o : propertySet) {
			Map.Entry entry = (Map.Entry) o;
			propMap.put(entry.getKey(), entry.getValue());
		}

	}

	public static void main(String[] args) {
		Properties properties = new Properties();

		properties.setProperty("StrictHostKeyChecking", "no");
		properties.setProperty("app.version", "1.0");

		Map map = new HashMap<>();

		prop2Map(properties, map);

		System.out.println(map);
	}
}
