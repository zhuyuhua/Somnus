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
package com.somnus.rpc.thrift.util;

import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class SerializationUtil {

	private static Logger logger = LoggerFactory.getLogger(SerializationUtil.class);

	private static final Factory factory = new TBinaryProtocol.Factory();

	/**
	 * 
	 * 对对象base进行序列化，保存到bytes中
	 * 
	 * @since 0.0.1
	 */
	@SuppressWarnings("rawtypes")
	public static byte[] base2bytes(TBase base) {
		try {
			return new TSerializer(factory).serialize(base);
		} catch (TException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 对bytes中保存的对象进行反序列化，保存到base中
	 * 
	 * @param base
	 * @param bytes
	 * @exception @since
	 *                0.0.1
	 */
	public static void bytes2base(@SuppressWarnings("rawtypes") TBase base, byte[] bytes) {
		try {
			new TDeserializer(factory).deserialize(base, bytes);
		} catch (TException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
}
