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
package com.somnus.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 表明指定name在缓存配置文件中不存在
 * 
 * @author:zhuyuhua
 * @date:2015年7月5日 下午10:05:10
 * @version 0.0.1
 */
public class UnExistsedCacheNameException extends RuntimeException {

	private static Logger logger = LoggerFactory.getLogger(UnExistsedCacheNameException.class);

	public UnExistsedCacheNameException() {
		super("指定的缓存不存在");
	}
}
