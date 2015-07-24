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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties配置文件的读，写，修改操作的辅助类
 * 
 * @author zhuyuhua
 * @since 2015年7月19日
 */
public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * properties所在的路径
	 */
	private String propertyPath;

	private PropertiesUtil() {
	}

	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 
	 * TODO
	 * 
	 * @return PropertiesUtil
	 * @since 0.0.1
	 */
	public static PropertiesUtil getInstance(String path) {
		PropertiesUtil util = new PropertiesUtil();
		Properties properties = new Properties();
		try {

			properties.load(new FileInputStream(path));
			util.propertyPath = path;
			util.properties = properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return util;
	}

	/**
	 * 读取Properties值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 修改某个KEY-VALUE值
	 * 
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * 在文件中新增KEY-VALUE值
	 * 
	 * @param key
	 * @param value
	 */
	public void addProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * 删除Properties中的某个KEY值
	 * 
	 * @param key
	 */
	public void removeProperty(String key) {
		properties.remove(key);
	}

	/**
	 * 对修改后的properties进行重建
	 */
	public void restore() {
		OutputStream out = null;
		try {
			out = new FileOutputStream(propertyPath);
			properties.store(out, "auto modify");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void printlnProperties() {
		if (properties == null) {
			return;
		}
		Object[] obj = properties.keySet().toArray();
		for (Object object : obj) {
			System.out.println("key:" + object + ",value:" + properties.get(object));
		}
	}

}
