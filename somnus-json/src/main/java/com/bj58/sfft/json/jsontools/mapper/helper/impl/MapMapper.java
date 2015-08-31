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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bj58.sfft.json.jsontools.mapper.JSONMapper;
import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.mapper.helper.ComplexMapperHelper;
import com.bj58.sfft.json.jsontools.model.JSONObject;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class MapMapper implements ComplexMapperHelper {
	@Override
	public Class getHelpedClass() {
		return Map.class;
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		return toJava(aValue, aRequestedClass, new Type[0]);
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRawClass, Type[] aTypes) throws MapperException {
		Map lMapObj;
		try {
			lMapObj = (Map) aRawClass.newInstance();
		} catch (Exception e) {
			lMapObj = new LinkedHashMap();
		}

		JSONObject aObject = null;
		try {
			if (!aValue.isObject()) {
				throw new MapperException("MapMapper cannot map: " + aValue.getClass().getName());
			}
			if (!Map.class.isAssignableFrom(aRawClass)) {
				throw new MapperException("MapMapper cannot map: " + aValue.getClass().getName());
			}
			aObject = (JSONObject) aValue;
		} catch (Exception e) {
			return lMapObj;
		}

		if (aTypes.length == 0) {
			for (String lKey : aObject.getValue().keySet()) {
				JSONValue lVal = aObject.getValue().get(lKey);
				lMapObj.put(lKey, JSONMapper.toJava(lVal));
			}
		} else if (aTypes.length == 2) {
			if (!aTypes[0].equals(String.class)) {
				throw new MapperException("MapMapper currently only supports String keys.");
			}

			for (String lKey : aObject.getValue().keySet()) {
				JSONValue lVal = aObject.getValue().get(lKey);
				if ((aTypes[1] instanceof Class)) {
					lMapObj.put(lKey, JSONMapper.toJava(lVal, (Class) aTypes[1]));
				} else {
					lMapObj.put(lKey, JSONMapper.toJava(lVal, (ParameterizedType) aTypes[1]));
				}

			}

		} else {
			throw new MapperException("MapMapper cannot map: " + aValue.getClass().getName());
		}

		return lMapObj;
	}

	@Override
	public JSONValue toJSON(Object aPojo) throws MapperException {
		JSONObject lObj = new JSONObject();
		if (!Map.class.isAssignableFrom(aPojo.getClass())) {
			throw new MapperException("MapMapper cannot map: " + aPojo.getClass().getName());
		}

		Map lMap = (Map) aPojo;
		for (Iterator localIterator = lMap.keySet().iterator(); localIterator.hasNext();) {
			Object lKey = localIterator.next();

			lObj.getValue().put(lKey.toString(), JSONMapper.toJSON(lMap.get(lKey)));
		}
		return lObj;
	}
}