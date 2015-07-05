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
package com.somnus.utils.json;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

/**
 * @author:zhuyuhua
 * @date:2015年7月5日 下午10:27:11
 * @version 0.0.1
 */
public class GsonSerializerBuilder {
	private GsonBuilder builder;

	public GsonSerializerBuilder() {
		builder = new GsonBuilder();
	}

	public GsonSerializerBuilder(GsonBuilder builder) {
		this.builder = builder;
	}

	public GsonSerializerBuilder serializeNulls() {
		return new GsonSerializerBuilder(builder.serializeNulls());
	}

	public GsonSerializerBuilder excludeFieldsNamed(String... fieldNames) {
		return new GsonSerializerBuilder(
				builder.addSerializationExclusionStrategy(new FieldNameExclusionStrategy(fieldNames)));
	}

	public GsonSerializerBuilder excludeFieldsWithModifiers(int... modifiers) {
		return new GsonSerializerBuilder(builder.excludeFieldsWithModifiers(modifiers));
	}

	public GsonSerializerBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
		return new GsonSerializerBuilder(builder.addSerializationExclusionStrategy(strategy));
	}

	public GsonSerializerBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
		return new GsonSerializerBuilder(builder.addDeserializationExclusionStrategy(strategy));
	}

	public GsonSerializerBuilder registerTypeAdapter(Type type, Object typeAdapter) {
		return new GsonSerializerBuilder(builder.registerTypeAdapter(type, typeAdapter));
	}

	public GsonSerializerBuilder prettyPrinting() {
		return new GsonSerializerBuilder(builder.setPrettyPrinting());
	}

	public GsonSerializerBuilder dateFormat(String pattern) {
		return new GsonSerializerBuilder(builder.setDateFormat(pattern));
	}

	public GsonSerializer build() {
		return new GsonSerializer(builder.create());
	}

	private static class FieldNameExclusionStrategy implements ExclusionStrategy {

		private List<String> fieldNames;

		private FieldNameExclusionStrategy(String... fieldNames) {
			this.fieldNames = Arrays.asList(fieldNames);
		}

		@Override
		public boolean shouldSkipField(FieldAttributes fieldAttributes) {
			return fieldNames.contains(fieldAttributes.getName());
		}

		@Override
		public boolean shouldSkipClass(Class<?> aClass) {
			return false;
		}
	}

}
