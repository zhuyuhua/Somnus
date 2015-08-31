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

import java.math.BigDecimal;

import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.mapper.helper.SimpleMapperHelper;
import com.bj58.sfft.json.jsontools.model.JSONDecimal;
import com.bj58.sfft.json.jsontools.model.JSONInteger;
import com.bj58.sfft.json.jsontools.model.JSONString;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class FloatMapper implements SimpleMapperHelper {
	@Override
	public Class getHelpedClass() {
		return Float.class;
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		if (aValue.isString()) {
			try {
				return Float.valueOf(Float.parseFloat(((JSONString) aValue).getValue()));
			} catch (NumberFormatException e) {
				throw new MapperException("FloatMapper cannot map value: " + ((JSONString) aValue).getValue());
			}
		}
		if (aValue.isDecimal()) {
			return Float.valueOf(((JSONDecimal) aValue).getValue().floatValue());
		}
		if (aValue.isInteger()) {
			return Float.valueOf(((JSONInteger) aValue).getValue().floatValue());
		}
		throw new MapperException("FloatMapper cannot map: " + aValue.getClass().getName());
	}

	@Override
	public JSONValue toJSON(Object aPojo) throws MapperException {
		if (!Float.class.isAssignableFrom(aPojo.getClass())) {
			throw new MapperException("FloatMapper cannot map: " + aPojo.getClass().getName());
		}
		return new JSONDecimal(new BigDecimal(aPojo.toString()));
	}
}