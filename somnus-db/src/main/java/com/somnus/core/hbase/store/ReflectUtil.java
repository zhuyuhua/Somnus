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
package com.somnus.core.hbase.store;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.core.hbase.domain.Attribute;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class ReflectUtil {

	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	public static <T> List<Attribute> getAttributes(T object) {
		Class<? extends Object> class1 = object.getClass();

		List<Attribute> attributes = new ArrayList<Attribute>();

		try {
			Field[] fields = class1.getDeclaredFields();
			for (Field field : fields) {
				String name = field.getName();
				field.setAccessible(true);
				// name=name+"@"+field.getType().getName();
				logger.debug(name);
				Object value = field.get(object);
				String strJson = JacksonUtils.writeEntity2JSON(value);
				Attribute attribute = new Attribute(name, strJson);
				attributes.add(attribute);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return attributes;
	}

	public static <T> T getObject(Map<String, Attribute> allAttribute,
			String className) throws Exception {

		Class<T> classz = (Class<T>) Class.forName(className);
		T t = classz.newInstance();

		Field[] fields = classz.getDeclaredFields();
		for (Field field : fields) {
			Attribute attribute = allAttribute.get(field.getName());
			Class<?> type = field.getType();
			Object value = JacksonUtils.readJson2Entity(attribute.getValue(),
					type);
			field.setAccessible(true);
			field.set(t, value);
		}

		return t;
	}

}
