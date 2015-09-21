/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.store;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pingan.karma.domain.Attribute;
import com.somnus.core.bigdata.utils.json.jackson.JacksonUtils;

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
