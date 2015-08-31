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
package com.bj58.sfft.json.jsontools.mapper.helper.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.bj58.sfft.json.jsontools.mapper.JSONMapper;
import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.mapper.helper.SimpleMapperHelper;
import com.bj58.sfft.json.jsontools.model.JSONObject;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ObjectMapper implements SimpleMapperHelper {

	@Override
	public Class getHelpedClass() {
		return Object.class;
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		if (!aValue.isObject()) {
			throw new MapperException("ObjectMapper cannot map: " + aValue.getClass().getName());
		}
		JSONObject aObject = (JSONObject) aValue;
		try {
			Object lBean = aRequestedClass.newInstance();

			for (String lPropname : aObject.getValue().keySet()) {
				JSONValue lSubEl = aObject.get(lPropname);

				boolean lFoundWriter = false;
				PropertyDescriptor[] lPropDesc = Introspector.getBeanInfo(aRequestedClass, 1).getPropertyDescriptors();
				for (PropertyDescriptor aLPropDesc : lPropDesc) {
					if (aLPropDesc.getName().equalsIgnoreCase(lPropname)) {
						lFoundWriter = true;
						Method lWriter = aLPropDesc.getWriteMethod();
						if (lWriter == null) {
							break;
						}

						Type[] lTypes = lWriter.getGenericParameterTypes();
						Object lProp;
						if ((lTypes.length == 1) && ((lTypes[0] instanceof ParameterizedType))) {
							lProp = JSONMapper.toJava(lSubEl, (ParameterizedType) lTypes[0]);
						} else {
							lProp = JSONMapper.toJava(lSubEl, aLPropDesc.getPropertyType());
						}

						lWriter.invoke(lBean, new Object[] { lProp });

						break;
					}
				}

				if (!lFoundWriter) {
					String lMsg = "Could not find a setter for prop: " + lPropname + " in class: " + aRequestedClass;
					throw new MapperException(lMsg);
				}
			}
			return lBean;
		} catch (IllegalAccessException e) {
			String lMsg = "IllegalAccessException while trying to instantiate bean: " + aRequestedClass;
			throw new MapperException(lMsg);
		} catch (InstantiationException e) {
			String lMsg = "InstantiationException while trying to instantiate bean: " + aRequestedClass;
			throw new MapperException(lMsg);
		} catch (IntrospectionException e) {
			String lMsg = "IntrospectionException while trying to fill bean: " + aRequestedClass;
			throw new MapperException(lMsg);
		} catch (InvocationTargetException e) {
			String lMsg = "InvocationTargetException while trying to fill bean: " + aRequestedClass;
			throw new MapperException(lMsg);
		}
	}

	@Override
	public JSONValue toJSON(Object aPojo) throws MapperException {
		JSONObject lElements = new JSONObject();
		String lPropName = "";
		try {
			Class lClass = aPojo.getClass();

			List<Field> fieldList = getAllFields(aPojo);
			PropertyDescriptor[] lPropDesc = Introspector.getBeanInfo(lClass, 1).getPropertyDescriptors();
			for (PropertyDescriptor aLPropDesc : lPropDesc) {
				Method lReader = aLPropDesc.getReadMethod();
				lPropName = aLPropDesc.getName();

				for (Field f : fieldList) {
					if (lPropName.equalsIgnoreCase(f.getName())) {
						lPropName = f.getName();
						break;
					}

				}

				if ((lReader == null) || ((!lReader.getReturnType().toString().contains("net.sf.cglib.proxy.Callback"))
						&& (!lReader.getReturnType().toString().contains("org.hibernate.proxy.LazyInitializer")))) {
					if ((lReader == null) || (!lPropName.equals("class"))) {
						if (lReader != null) {
							lElements.getValue().put(lPropName,
									JSONMapper.toJSON(lReader.invoke(aPojo, new Object[0])));
						}
					}
				}
			}
			return lElements;
		} catch (IntrospectionException e) {
			String lMsg = "Error while introspecting JavaBean. Class: " + aPojo.getClass();
			throw new MapperException(lMsg);
		} catch (IllegalAccessException e) {
			String lMsg = "Illegal access while trying to fetch a bean property (1).Property: " + lPropName
					+ " Object: " + aPojo;
			throw new MapperException(lMsg);
		} catch (InvocationTargetException e) {
			String lMsg = "Illegal access while trying to fetch a bean property (2).Property: " + lPropName
					+ " Object: " + aPojo;
			throw new MapperException(lMsg);
		}
	}

	public static List<Field> getAllFields(Object bean) {
		List fieldList = new ArrayList();
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field f : fields) {
			fieldList.add(f);
		}
		return fieldList;
	}
}
