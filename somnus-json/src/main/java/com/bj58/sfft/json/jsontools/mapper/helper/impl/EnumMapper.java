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

import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.model.JSONString;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class EnumMapper extends AbstractMapper {
	@Override
	public Class getHelpedClass() {
		return Enum.class;
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		if (!aValue.isString()) {
			throw new MapperException("EnumMapper cannot map class: " + aValue.getClass().getName());
		}

		if (aRequestedClass.isEnum()) {
			Object[] lEnumVals = aRequestedClass.getEnumConstants();
			for (Object lEnumVal : lEnumVals) {
				if (lEnumVal.toString().equals(((JSONString) aValue).getValue())) {
					return lEnumVal;
				}
			}
		} else {
			String lMsg = "Enum mapper tried to handle a non-enum class: " + aRequestedClass;
			throw new MapperException(lMsg);
		}

		String lMsg = "The enum class *is found* but no matching value could be found. Enum Class: " + aRequestedClass
				+ ", unknown value: " + ((JSONString) aValue).getValue();
		throw new MapperException(lMsg);
	}
}