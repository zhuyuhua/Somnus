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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.bj58.sfft.json.jsontools.mapper.JSONMapper;
import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.mapper.helper.SimpleMapperHelper;
import com.bj58.sfft.json.jsontools.model.JSONArray;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ArrayMapper implements SimpleMapperHelper {
	@Override
	public JSONValue toJSON(Object aObj) throws MapperException {
		Class lClass = aObj.getClass();
		String lObjClassName = lClass.getName();

		String lComponentName = "unknown";
		if (lObjClassName.startsWith("[L")) {
			lComponentName = lObjClassName.substring(2, lObjClassName.length() - 1);
		} else {
			lComponentName = lObjClassName.substring(1);
		}
		JSONArray lElements = new JSONArray();

		if (isPrimitiveArray(lComponentName)) {
			if ("I".equals(lComponentName)) {
				int[] lArr = (int[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Integer.valueOf(lArr[i])));
				}
			}
			if ("C".equals(lComponentName)) {
				char[] lArr = (char[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Character.valueOf(lArr[i])));
				}
			} else if ("Z".equals(lComponentName)) {
				boolean[] lArr = (boolean[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Boolean.valueOf(lArr[i])));
				}
			} else if ("S".equals(lComponentName)) {
				short[] lArr = (short[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Short.valueOf(lArr[i])));
				}
			} else if ("B".equals(lComponentName)) {
				byte[] lArr = (byte[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Byte.valueOf(lArr[i])));
				}
			} else if ("J".equals(lComponentName)) {
				long[] lArr = (long[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Long.valueOf(lArr[i])));
				}
			} else if ("F".equals(lComponentName)) {
				float[] lArr = (float[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Float.valueOf(lArr[i])));
				}
			} else if ("D".equals(lComponentName)) {
				double[] lArr = (double[]) aObj;
				for (int i = 0; i < lArr.length; i++) {
					lElements.getValue().add(JSONMapper.toJSON(Double.valueOf(lArr[i])));
				}
			}
		} else {
			Iterator lIter = Arrays.asList((Object[]) aObj).iterator();
			while (lIter.hasNext()) {
				Object lArrEl = lIter.next();
				lElements.getValue().add(JSONMapper.toJSON(lArrEl));
			}
		}
		return lElements;
	}

	@Override
	public Class getHelpedClass() {
		return null;
	}

	private boolean isPrimitiveArray(String aClassName) {
		return ("I".equals(aClassName)) || ("Z".equals(aClassName)) || ("S".equals(aClassName))
				|| ("B".equals(aClassName)) || ("J".equals(aClassName)) || ("F".equals(aClassName))
				|| ("D".equals(aClassName)) || ("C".equals(aClassName));
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		if (!aValue.isArray()) {
			throw new MapperException("ArrayMapper cannot map: " + aValue.getClass().getName());
		}

		JSONArray lValues = (JSONArray) aValue;

		String lObjClassName = aRequestedClass.getName();

		String lArrClassName = "unknown";
		if (lObjClassName.startsWith("[L")) {
			lArrClassName = lObjClassName.substring(2, lObjClassName.length() - 1);
		} else {
			lArrClassName = lObjClassName.substring(1);
		}
		List lElements = new LinkedList();
		for (JSONValue jsonValue : lValues.getValue()) {
			try {
				if (isPrimitiveArray(lArrClassName)) {
					Class primitiveClass = null;
					if ("I".equals(lArrClassName)) {
						primitiveClass = Integer.class;
					} else if ("C".equals(lArrClassName)) {
						primitiveClass = Character.class;
					} else if ("Z".equals(lArrClassName)) {
						primitiveClass = Boolean.class;
					} else if ("S".equals(lArrClassName)) {
						primitiveClass = Short.class;
					} else if ("B".equals(lArrClassName)) {
						primitiveClass = Byte.class;
					} else if ("J".equals(lArrClassName)) {
						primitiveClass = Long.class;
					} else if ("F".equals(lArrClassName)) {
						primitiveClass = Float.class;
					} else if ("D".equals(lArrClassName)) {
						primitiveClass = Double.class;
					} else {
						String lMsg = "Unknown primitive array type: " + lArrClassName;
						throw new MapperException(lMsg);
					}
					lElements.add(JSONMapper.toJava(jsonValue, primitiveClass));
				} else {
					lElements.add(JSONMapper.toJava(jsonValue, Class.forName(lArrClassName)));
				}

			} catch (ClassNotFoundException e) {
				throw new MapperException("No Class Found: " + lArrClassName);
			}
		}
		int lArrSize = lElements.size();

		if (isPrimitiveArray(lArrClassName)) {
			if ("I".equals(lArrClassName)) {
				int[] lArr = new int[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Integer) lIter.next()).intValue();
					i++;
				}
				return lArr;
			}
			if ("C".equals(lArrClassName)) {
				char[] lArr = new char[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Character) lIter.next()).charValue();
					i++;
				}
				return lArr;
			}
			if ("Z".equals(lArrClassName)) {
				boolean[] lArr = new boolean[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Boolean) lIter.next()).booleanValue();
					i++;
				}
				return lArr;
			}
			if ("S".equals(lArrClassName)) {
				short[] lArr = new short[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Short) lIter.next()).shortValue();
					i++;
				}
				return lArr;
			}
			if ("B".equals(lArrClassName)) {
				byte[] lArr = new byte[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Byte) lIter.next()).byteValue();
					i++;
				}
				return lArr;
			}
			if ("J".equals(lArrClassName)) {
				long[] lArr = new long[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Long) lIter.next()).longValue();
					i++;
				}
				return lArr;
			}
			if ("F".equals(lArrClassName)) {
				float[] lArr = new float[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Float) lIter.next()).floatValue();
					i++;
				}
				return lArr;
			}
			if ("D".equals(lArrClassName)) {
				double[] lArr = new double[lArrSize];
				Iterator lIter = lElements.iterator();
				int i = 0;
				while (lIter.hasNext()) {
					lArr[i] = ((Double) lIter.next()).doubleValue();
					i++;
				}
				return lArr;
			}

			String lMsg = "Unknown primitive array type: " + lArrClassName;
			throw new MapperException(lMsg);
		}

		try {
			Class lComponentClass = Class.forName(lArrClassName);
			Object lArr = Array.newInstance(lComponentClass, lArrSize);
			Iterator lIter = lElements.iterator();
			int i = 0;
			while (lIter.hasNext()) {
				Array.set(lArr, i, lIter.next());
				i++;
			}
			return lArr;
		} catch (ClassNotFoundException e) {
			String lMsg = "Exception while trying to unmarshall an array of JavaObjects: " + lArrClassName;
			throw new MapperException(lMsg);
		}
	}
}