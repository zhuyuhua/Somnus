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
package com.somnus.protocol.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Json格式序列化
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONSerialize extends SerializeBase {

	private static Logger logger = LoggerFactory.getLogger(JSONSerialize.class);

	@Override
	public byte[] serialize(Object obj) throws Exception {
		throw new UnsupportedOperationException("Not supported json serialize!");
	}

	@Override
	public Object deserialize(byte[] data, Class<?> cls) throws Exception {
		throw new UnsupportedOperationException("Not supported json serialize!");
	}
}
