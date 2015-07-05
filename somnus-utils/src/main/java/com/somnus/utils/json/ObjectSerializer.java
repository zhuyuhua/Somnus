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

/**
 * 对象序列化器，将Java对象序列化为字符串形式，或者相反，从字符串反序列化为对象。
 * 
 * @author:zhuyuhua
 * @date:2015年7月5日 下午10:27:11
 * @version 0.0.1
 */
public interface ObjectSerializer {

	/**
	 * 将对象序列化为字符串
	 * 
	 * @param anObject
	 *            要序列化的对象
	 * @return 对象的序列化形式
	 */
	String serialize(Object anObject);

	/**
	 * 将字符串反序列化为对象
	 * 
	 * @param serializedString
	 *            对象的字符串序列化形式
	 * @param objectClass
	 *            对象的类
	 * @param <T>
	 *            对象的类型
	 * @return 一个对象实例
	 */
	<T> T deserialize(String serializedString, Class<T> objectClass);
}
