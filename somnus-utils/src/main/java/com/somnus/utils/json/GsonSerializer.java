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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.somnus.utils.serializer.ObjectSerializer;

/**
 * @author zhuyuhua
 * @date:2015年7月5日 下午10:27:11
 * @version 0.0.1
 */
public class GsonSerializer implements ObjectSerializer {

	private Gson gson;

	public GsonSerializer() {
		this.gson = new GsonBuilder().create();
	}

	public GsonSerializer(Gson gson) {
		this.gson = gson;
	}

	@Override
	public String serialize(Object anObject) {
		return gson.toJson(anObject);
	}

	@Override
	public <T> T deserialize(String serializedString, Class<T> objectClass) {
		return gson.fromJson(serializedString, objectClass);
	}

}
