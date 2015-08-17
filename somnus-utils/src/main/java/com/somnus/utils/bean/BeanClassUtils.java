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
package com.somnus.utils.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 针对JavaBean Class的工具类。
 * 
 * @author zhuyuhua
 * @date:2015年7月5日 下午10:31:28
 * @version 0.0.1
 */
public class BeanClassUtils {

	private static Logger logger = LoggerFactory.getLogger(BeanClassUtils.class);
	private final Class<?> clazz;

	/**
	 * 接受一个类，生成BeanClassUtils实例
	 *
	 * @param clazz
	 *            原始类
	 */
	public BeanClassUtils(final Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 获得所有属性的类型，包括从父类继承的属性
	 *
	 * @return 一个Map，Key为属性名， Value为属性所属的类
	 */
	public Map<String, Class<?>> getPropTypes() {
		Map<String, Class<?>> results = new HashMap<String, Class<?>>();
		for (Map.Entry<String, PropertyDescriptor> each : getPropertyDescriptors().entrySet()) {
			results.put(each.getKey(), each.getValue().getPropertyType());
		}
		return results;
	}

	/**
	 * 获得指定JavaBean类型的所有属性的名字，包括从父类继承的属性
	 *
	 * @return JavaBean的属性名的集合
	 */
	public Set<String> getPropNames() {
		return getPropertyDescriptors().keySet();
	}

	/**
	 * 获得指定JavaBean类型的所有可读属性的名字，包括从父类继承的属性
	 *
	 * @return JavaBean的属性名的集合
	 */
	public Set<String> getReadablePropNames() {
		Set<String> results = new HashSet<String>();
		for (Map.Entry<String, PropertyDescriptor> each : getPropertyDescriptors().entrySet()) {
			if (each.getValue().getReadMethod() == null) {
				continue;
			}
			results.add(each.getKey());
		}
		return results;
	}

	/**
	 * 获得指定JavaBean类型的所有可写属性的名字，包括从父类继承的属性
	 *
	 * @return JavaBean的属性名的集合
	 */
	public Set<String> getWritablePropNames() {
		Set<String> results = new HashSet<String>();
		for (Map.Entry<String, PropertyDescriptor> each : getPropertyDescriptors().entrySet()) {
			if (each.getValue().getWriteMethod() == null) {
				continue;
			}
			results.add(each.getKey());
		}
		return results;
	}

	/**
	 * 获得JavaBean的属性值的值，包括从父类继承的属性，不包含指定的属性。
	 *
	 * @param excludePropNames
	 *            要排除的属性名
	 * @return 一个Map，其中Key为属性名，Value为属性值。
	 */
	public Set<String> getReadablePropNamesExclude(String... excludePropNames) {
		List<String> propNamesExclude = Arrays.asList(excludePropNames);
		Set<String> results = new HashSet<String>();
		for (String propName : getReadablePropNames()) {
			if (propNamesExclude.contains(propName)) {
				continue;
			}
			results.add(propName);
		}
		return results;
	}

	/**
	 * 获得JavaBean的属性值的值，包括从父类继承的属性，不包含指定由指定Annotation标记的的属性。
	 *
	 * @param excludeAnnotations
	 *            一批Annotation，被这些Annotation标注的属性将被排除
	 * @return 一个Map，其中Key为属性名，Value为属性值。
	 */
	public Set<String> getReadablePropNamesExclude(Class<? extends Annotation>... excludeAnnotations) {
		List<Class<? extends Annotation>> annotationsExclude = Arrays.asList(excludeAnnotations);
		Set<String> results = new HashSet<String>();
		Map<String, PropertyDescriptor> props = getPropertyDescriptors();
		for (String propName : getReadablePropNames()) {
			PropertyDescriptor propertyDescriptor = props.get(propName);
			Method readMethod = propertyDescriptor.getReadMethod();
			if (readMethod == null) {
				continue;
			}
			if (methodContainsAnnotation(readMethod, annotationsExclude)) {
				continue;
			}
			results.add(propName);
		}
		return results;
	}

	/**
	 * 获得类的属性描述
	 *
	 * @return 类的属性描述的Map，Key为属性名，Value为属性描述对象
	 */
	Map<String, PropertyDescriptor> getPropertyDescriptors() {
		Map<String, PropertyDescriptor> results = new HashMap<String, PropertyDescriptor>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				results.put(propertyDescriptor.getName(), propertyDescriptor);
			}
			results.remove("class");
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	private boolean methodContainsAnnotation(Method readMethod, List<Class<? extends Annotation>> annotationsExclude) {
		for (Class<? extends Annotation> annotationClass : annotationsExclude) {
			if (readMethod.isAnnotationPresent(annotationClass)) {
				return true;
			}
		}
		return false;
	}
}
