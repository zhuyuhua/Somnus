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
package com.somnus.rpc.server.context;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ServiceConfig {

	private static Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

	private static Map<String, String> property = null;

	private ServiceConfig() {
		property = new HashMap<String, String>();
	}

	public void set(String key, String value) {
		property.put(key, value);
	}

	public String getString(String name) {
		return property.get(name);
	}

	public int getInt(String name) throws Exception {
		String value = property.get(name);
		if (value == null || value.equalsIgnoreCase("")) {
			throw new Exception("the property (" + name + ") is null");
		}
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String name) throws Exception {
		String value = property.get(name);
		if (value == null || value.equalsIgnoreCase("")) {
			throw new Exception("the property (" + name + ") is null");
		}
		return Boolean.parseBoolean(value);
	}

	public List<String> getList(String name, String split) {
		String value = property.get(name);
		if (value == null || value.equalsIgnoreCase("")) {
			return null;
		}

		List<String> list = new ArrayList<String>();
		String[] values = value.split(split);
		for (String v : values) {
			list.add(v);
		}
		return list;
	}

	/**
	 * 加载配置文件
	 * 
	 * @param configPaths
	 * @return SerivceConfig
	 */
	public static ServiceConfig getServiceConfig(String... configPaths) {
		ServiceConfig instance = new ServiceConfig();

		for (String configPath : configPaths) {
			File fServiceConfig = new File(configPath);
			if (!fServiceConfig.exists()) {
				continue;
			}
		}
		return instance;
	}

}
