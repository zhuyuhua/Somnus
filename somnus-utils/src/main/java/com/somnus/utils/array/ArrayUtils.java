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
package com.somnus.utils.array;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.utils.bean.BeanUtils;

/**
 * TODO
 * 
 * @author:zhuyuhua
 * @date:2015年7月5日 下午10:30:11
 * @version 0.0.1
 */
public class ArrayUtils {

	private static Logger logger = LoggerFactory.getLogger(ArrayUtils.class);

	private ArrayUtils() {
	}

	/**
	 * 抽取数组中每个元素的一个属性值形成新的数组。
	 *
	 * @param items
	 *            原始数组
	 * @param property
	 *            要抽取的数组元素属性
	 * @return 由原始数组每个元素的一个指定属性的值组成的数组
	 */
	public static Object[] substract(Object[] items, String property) {
		if (items.length == 0) {
			return new Object[0];
		}
		Object[] results = new Object[items.length];
		for (int i = 0; i < items.length; i++) {
			Object item = items[i];
			Map<String, Object> propValues = new BeanUtils(item).getPropValues();
			if (!propValues.containsKey(property)) {
				throw new IllegalArgumentException("Property " + property + " not exists!");
			}
			results[i] = propValues.get(property);
		}
		return results;
	}

	/**
	 * 抽取数组中每个元素的一个属性形成新的数组，然后用指定的分隔符连接起来形成一个字符串。
	 *
	 * @param items
	 *            原始数组
	 * @param property
	 *            要抽取的数组元素属性
	 * @param separator
	 *            字符串分隔符
	 * @return 由原始数组每个元素的指定属性的值按指定的分隔符连接起来形成的一个字符串。
	 */
	public static String join(Object[] items, String property, String separator) {
		if (items == null || items.length == 0) {
			return "";
		}
		return StringUtils.join(substract(items, property), separator);
	}
}
