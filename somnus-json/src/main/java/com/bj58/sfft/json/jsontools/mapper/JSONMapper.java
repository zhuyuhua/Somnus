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
package com.bj58.sfft.json.jsontools.mapper;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;

import com.bj58.sfft.json.jsontools.helper.HelperRepository;
import com.bj58.sfft.json.jsontools.mapper.helper.SimpleMapperHelper;
import com.bj58.sfft.json.jsontools.mapper.helper.impl.ObjectMapper;
import com.bj58.sfft.json.jsontools.model.JSONValue;

import net.sf.json.JSONNull;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月27日
 */
public class JSONMapper {

	private static HelperRepository<SimpleMapperHelper> repo = new HelperRepository();

	static {
		repo.addHelper(new ObjectMapper());

		repo.addHelper(new StringMapper());
		repo.addHelper(new BooleanMapper());
		repo.addHelper(new ByteMapper());
		repo.addHelper(new ShortMapper());

		repo.addHelper(new IntegerMapper());

		repo.addHelper(new LongMapper());
		repo.addHelper(new FloatMapper());
		repo.addHelper(new DoubleMapper());
		repo.addHelper(new BigIntegerMapper());
		repo.addHelper(new BigDecimalMapper());
		repo.addHelper(new CharacterMapper());
		repo.addHelper(new DateMapper());
		repo.addHelper(new CollectionMapper());
		repo.addHelper(new MapMapper());
		repo.addHelper(new EnumMapper());
	}

	public static Object toJava(JSONValue aValue, Class aPojoClass) throws MapperException {
		if (aValue == null) {
			String lMsg = "Mapper does not support null values.";
			throw new MapperException("Mapper does not support null values.");
		}

		if (aValue.isNull()) {
			return null;
		}
		if (aPojoClass.isArray()) {
			ArrayMapper arrayMapper = new ArrayMapper();
			return arrayMapper.toJava(aValue, aPojoClass);
		}

		if (aPojoClass == Boolean.TYPE) {
			aPojoClass = Boolean.class;
		} else if (aPojoClass == Byte.TYPE) {
			aPojoClass = Byte.class;
		} else if (aPojoClass == Short.TYPE) {
			aPojoClass = Short.class;
		} else if (aPojoClass == Integer.TYPE) {
			aPojoClass = Integer.class;
		} else if (aPojoClass == Long.TYPE) {
			aPojoClass = Long.class;
		} else if (aPojoClass == Float.TYPE) {
			aPojoClass = Float.class;
		} else if (aPojoClass == Double.TYPE) {
			aPojoClass = Double.class;
		} else if (aPojoClass == Character.TYPE) {
			aPojoClass = Character.class;
		}

		SimpleMapperHelper lHelperSimple = repo.findHelper(aPojoClass);

		if (lHelperSimple == null) {
			String lMsg = "Could not find a mapper helper for class: " + aPojoClass.getName();
			throw new MapperException(lMsg);
		}

		return lHelperSimple.toJava(aValue, aPojoClass);
	}

	public static Object toJava(JSONValue aValue, ParameterizedType aGenericType) throws MapperException {
		if (aValue == null) {
			String lMsg = "Mapper does not support null values.";
			throw new MapperException("Mapper does not support null values.");
		}

		if (aValue.isNull()) {
			return null;
		}

		Class lRawClass = (Class) aGenericType.getRawType();
		Type[] lTypes = aGenericType.getActualTypeArguments();

		SimpleMapperHelper lMapperHelper = repo.findHelper(lRawClass);

		if (lMapperHelper == null) {
			String lMsg = "Could not find a mapper helper for parameterized type: " + aGenericType;
			throw new MapperException(lMsg);
		}

		if ((lMapperHelper instanceof ComplexMapperHelper)) {
			return ((ComplexMapperHelper) lMapperHelper).toJava(aValue, lRawClass, lTypes);
		}
		return lMapperHelper.toJava(aValue, lRawClass);
	}

	public static Object toJava(JSONValue aValue) throws MapperException {
		if (aValue.isArray()) {
			return toJava(aValue, LinkedList.class);
		}
		if (aValue.isBoolean()) {
			return toJava(aValue, Boolean.class);
		}
		if (aValue.isDecimal()) {
			return toJava(aValue, BigDecimal.class);
		}
		if (aValue.isInteger()) {
			return toJava(aValue, BigInteger.class);
		}
		if (aValue.isString()) {
			return toJava(aValue, String.class);
		}
		return toJava(aValue, Object.class);
	}

	public static JSONValue toJSON(Object aPojo) throws MapperException {
		if (aPojo == null) {
			return JSONNull.NULL;
		}
		Class lObjectClass = aPojo.getClass();
		if (lObjectClass.isArray()) {
			ArrayMapper arrayMapper = new ArrayMapper();
			return arrayMapper.toJSON(aPojo);
		}

		SimpleMapperHelper lHelperSimple = repo.findHelper(aPojo.getClass());

		if (lHelperSimple == null) {
			String lMsg = "Could not find a mapper helper for class: " + aPojo.getClass().getName();
			throw new MapperException(lMsg);
		}
		return lHelperSimple.toJSON(aPojo);
	}

	public static void addHelper(SimpleMapperHelper aHelper) {
		repo.addHelper(aHelper);
	}

	public static HelperRepository<SimpleMapperHelper> getRepository() {
		return repo;
	}

	public static void usePojoAccess() {
		addHelper(new ObjectMapperDirect());
	}

	public static void useJavaBeanAccess() {
		addHelper(new ObjectMapper());
	}
}
