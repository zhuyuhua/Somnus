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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.bj58.sfft.json.jsontools.mapper.JSONMapper;
import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.mapper.helper.ComplexMapperHelper;
import com.bj58.sfft.json.jsontools.model.JSONArray;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class CollectionMapper implements ComplexMapperHelper {
	@Override
	public Class getHelpedClass() {
		return Collection.class;
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		return toJava(aValue, aRequestedClass, new Type[0]);
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRawClass, Type[] aTypes) throws MapperException {
		Collection lCollObj;
		try {
			if (aRawClass.isInterface()) {
				if (aRawClass == Set.class) {
					lCollObj = new HashSet();
				} else {
					if (aRawClass == SortedSet.class) {
						lCollObj = new TreeSet();
					} else {
						lCollObj = new LinkedList();
					}
				}
			} else {
				lCollObj = (Collection) aRawClass.newInstance();
			}
		} catch (Exception e) {
			lCollObj = new LinkedList();
		}

		JSONArray aObject = null;
		try {
			if (!aValue.isArray()) {
				throw new MapperException("CollectionMapper cannot map: " + aValue.getClass().getName());
			}
			if (!Collection.class.isAssignableFrom(aRawClass)) {
				throw new MapperException("CollectionMapper cannot map: " + aValue.getClass().getName());
			}
			aObject = (JSONArray) aValue;
		} catch (Exception e) {
			return lCollObj;
		}

		if (aTypes.length == 0) {
			for (JSONValue lVal : aObject.getValue()) {
				lCollObj.add(JSONMapper.toJava(lVal));
			}
		} else if (aTypes.length == 1) {
			for (JSONValue lVal : aObject.getValue()) {
				if ((aTypes[0] instanceof Class)) {
					lCollObj.add(JSONMapper.toJava(lVal, (Class) aTypes[0]));
				} else {
					lCollObj.add(JSONMapper.toJava(lVal, (ParameterizedType) aTypes[0]));
				}
			}

		} else {
			throw new MapperException("CollectionMapper cannot map: " + aValue.getClass().getName());
		}

		return lCollObj;
	}

	@Override
	public JSONValue toJSON(Object aPojo) throws MapperException {
		JSONArray lArray = new JSONArray();
		if (!Collection.class.isAssignableFrom(aPojo.getClass())) {
			throw new MapperException("CollectionMapper cannot map: " + aPojo.getClass().getName());
		}

		Collection lColl = (Collection) aPojo;
		for (Iterator localIterator = lColl.iterator(); localIterator.hasNext();) {
			Object lEl = localIterator.next();

			lArray.getValue().add(JSONMapper.toJSON(lEl));
		}
		return lArray;
	}
}