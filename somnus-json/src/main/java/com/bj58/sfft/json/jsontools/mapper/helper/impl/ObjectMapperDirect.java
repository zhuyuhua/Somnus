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

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bj58.sfft.json.jsontools.helper.JSONMap;
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
public class ObjectMapperDirect implements SimpleMapperHelper {
	private Map<Class, AnnotatedMethods> annotatedPool = new HashMap();

	@Override
	public Class getHelpedClass() {
		return Object.class;
	}

	protected Method getAnnotatedSerializingMethod(Class aClass) {
		for (Method lMethod : aClass.getDeclaredMethods()) {
			if (lMethod.isAnnotationPresent(JSONMap.class)) {
				lMethod.setAccessible(true);
				return lMethod;
			}
		}
		return null;
	}

	protected Constructor getAnnotatedConstructor(Class aClass) {
		Constructor[] lConstructors = aClass.getDeclaredConstructors();
		for (Constructor lCons : lConstructors) {
			if (lCons.isAnnotationPresent(JSONConstruct.class)) {
				lCons.setAccessible(true);
				return lCons;
			}
		}
		return null;
	}

	protected synchronized AnnotatedMethods getAnnotatedMethods(Class aClass) throws MapperException {
		AnnotatedMethods lResult = this.annotatedPool.get(aClass);
		if (lResult == null) {
			Constructor lCons = getAnnotatedConstructor(aClass);
			Method lMeth = getAnnotatedSerializingMethod(aClass);

			if (((lMeth == null) && (lCons != null)) || ((lMeth != null) && (lCons == null))) {
				throw new MapperException(String.format(
						"ObjectMapperDirect found inconsistency in class: '%1$s'. If annotated methods are used, it should contain both @JSONConstruct and @JSONMap together.",
						new Object[] { aClass.getClass().getName() }));
			}
			lResult = new AnnotatedMethods(lCons, lMeth);
			this.annotatedPool.put(aClass, lResult);
		}
		return lResult;
	}

	protected List<Field> getFieldInfo(Class aClass) {
		List lJavaFields = new LinkedList();
		Class lClassWalker = aClass;
		while (lClassWalker != null) {
			Field[] lClassFields = lClassWalker.getDeclaredFields();
			for (Field lFld : lClassFields) {
				int lModif = lFld.getModifiers();
				if ((!Modifier.isTransient(lModif)) && (!Modifier.isAbstract(lModif)) && (!Modifier.isStatic(lModif))
						&& (!Modifier.isFinal(lModif))) {
					lFld.setAccessible(true);
					lJavaFields.add(lFld);
				}
			}
			lClassWalker = lClassWalker.getSuperclass();
		}
		return lJavaFields;
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		if (!aValue.isObject()) {
			throw new MapperException("ObjectMapperDirect cannot map: " + aValue.getClass().getName());
		}
		JSONObject aObject = (JSONObject) aValue;
		try {
			AnnotatedMethods lAnnotated = getAnnotatedMethods(aRequestedClass);
			Object lResult;
			if (lAnnotated.cons != null) {
				int lCnt = lAnnotated.cons.getParameterTypes().length;
				Object[] lAttrs = new Object[lCnt];
				for (int i = 0; i < lCnt; i++) {
					String lFldName = "cons-" + i;
					JSONValue lSubEl = aObject.get(lFldName);
					try {
						lAttrs[i] = JSONMapper.toJava(lSubEl, lAnnotated.cons.getParameterTypes()[i]);
					} catch (MapperException e) {
						throw new MapperException(String.format(
								"ObjectMapperDirect error while deserializing. Error while calling the @JSONConstruct constructor in class: '%1$s' on parameter nr: %2$d with a value of class: '%3$s'.",
								new Object[] { aRequestedClass.getName(), Integer.valueOf(i),
										lAnnotated.cons.getParameterTypes()[i].getName() }),
								e);
					}

				}

				try {
					lResult = lAnnotated.cons.newInstance(lAttrs);
				} catch (Exception e) {
					throw new MapperException(String.format(
							"ObjectMapperDirect error while deserializing. Tried to instantiate an object (using annotated constructor) of class: '%1$s'.",
							new Object[] { aRequestedClass.getName() }), e);
				}

			} else {
				lResult = aRequestedClass.newInstance();
			}

			List lJavaFields = getFieldInfo(aRequestedClass);

			for (Iterator<String> e = aObject.getValue().keySet().iterator(); e.hasNext(); e.hasNext()) {
				String lPropname = e.next();

				JSONValue lSubEl = aObject.get(lPropname);
				e = lJavaFields.iterator();
				continue;
				Field lFld = (Field) e.next();

				if (lFld.getName().equals(lPropname)) {
					Type lGenType = lFld.getGenericType();
					Object lFldValue;
					Object lFldValue;
					if ((lGenType instanceof ParameterizedType)) {
						lFldValue = JSONMapper.toJava(lSubEl, (ParameterizedType) lGenType);
					} else {
						lFldValue = JSONMapper.toJava(lSubEl, lFld.getType());
					}

					try {
						lFld.setAccessible(true);
						lFld.set(lResult, lFldValue);
					} catch (Exception e) {
						throw new MapperException(String.format(
								"ObjectMapperDirect error while deserializing. Type error while trying to set the field: '%1$s' in class: '%2$s' with a value of class: '%3$s'.",
								new Object[] { lFld.getName(), aRequestedClass.getName(),
										lFldValue.getClass().getName() }),
								e);
					}

				}

			}

			if ((lResult instanceof Serializable)) {
				try {
					Method lReadResolve = lResult.getClass().getDeclaredMethod("readResolve", new Class[0]);
					if (lReadResolve != null) {
						lReadResolve.setAccessible(true);
						lResult = lReadResolve.invoke(lResult, new Object[0]);
					}

				} catch (NoSuchMethodException localNoSuchMethodException) {
				} catch (Exception e) {
					throw new MapperException(String.format(
							"ObjectMapperDirect error while creating java object. Tried to invoke 'readResolve' on instance of class: '%1$s'.",
							new Object[] { lResult.getClass().getName() }), e);
				}

			}

			return lResult;
		} catch (IllegalAccessException e) {
			String lMsg = "IllegalAccessException while trying to instantiate bean: " + aRequestedClass;
			throw new MapperException(lMsg);
		} catch (InstantiationException e) {
			String lMsg = "InstantiationException while trying to instantiate bean: " + aRequestedClass;
			throw new MapperException(lMsg);
		}
	}

	@Override
	public JSONValue toJSON(Object aPojo) throws MapperException {
		JSONObject lElements = new JSONObject();

		if ((aPojo instanceof Serializable)) {
			try {
				Method lWriteReplace = aPojo.getClass().getDeclaredMethod("writeReplace", new Class[0]);
				if (lWriteReplace != null) {
					lWriteReplace.setAccessible(true);
					return JSONMapper.toJSON(lWriteReplace.invoke(aPojo, new Object[0]));
				}

			} catch (NoSuchMethodException localNoSuchMethodException) {
			} catch (Exception e) {
				throw new MapperException(String.format(
						"ObjectMapperDirect error while trying to invoke 'writeReplace' on instance of class: '%1$s'.",
						new Object[] { aPojo.getClass().getName() }), e);
			}

		}

		Class lJavaClass = aPojo.getClass();
		List lJavaFields = getFieldInfo(lJavaClass);

		AnnotatedMethods lAnnotated = getAnnotatedMethods(lJavaClass);

		for (Field lFld : lJavaFields) {
			try {
				lFld.setAccessible(true);
				JSONValue lFieldVal = JSONMapper.toJSON(lFld.get(aPojo));
				lElements.getValue().put(lFld.getName(), lFieldVal);
			} catch (Exception e) {
				throw new MapperException(String.format(
						"ObjectMapperDirect error while serializing. Error while reading field: '%1$s' from instance of class: '%2$s'.",
						new Object[] { lFld.getName(), lJavaClass.getName() }), e);
			}

		}

		if (lAnnotated.serialize != null) {
			try {
				lVals = (Object[]) lAnnotated.serialize.invoke(aPojo, new Object[0]);
			} catch (Exception e) {
				Object[] lVals;
				throw new MapperException(String.format(
						"ObjectMapperDirect error while serializing. Error while invoking the @JSONMap method called '%1$s(...)' on an instance of class: '%2$s'.",
						new Object[] { lAnnotated.serialize.getName(), lJavaClass.getName() }), e);
			}
			Object[] lVals;
			int i = 0;
			try {
				for (Object lVal : lVals) {
					JSONValue lFieldVal = JSONMapper.toJSON(lVal);
					lElements.getValue().put("cons-" + i, lFieldVal);
					i++;
				}
			} catch (MapperException e) {
				throw new MapperException(String.format(
						"ObjectMapperDirect error while serializing. Error while serializing element nr %1$d from the @JSONMap method: '%2$s(...)' on instance of class: '%3$s'.",
						new Object[] { Integer.valueOf(i), lAnnotated.serialize.getName(), lJavaClass.getName() }), e);
			}
		}

		return lElements;
	}

	private static class AnnotatedMethods {
		public Constructor cons;
		public Method serialize;

		public AnnotatedMethods(Constructor aCons, Method aSerialize) {
			this.cons = aCons;
			this.serialize = aSerialize;
		}
	}
}