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
package com.somnus.rpc.client;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ClientConfigConstant {

	private static Logger logger = LoggerFactory.getLogger(ClientConfigConstant.class);

	protected static String DEFAULT_CONFIG_PATH = null;

	public static final long MAX_SESSIONID = 1024 * 1024 * 1024;

	public static final int DEFAULT_MAX_CURRENT_USER_COUNT = 2000;
	// 1m
	public static final int DEFAULT_MAX_PAKAGE_SIZE = 1024 * 1024;
	// 10kb
	public static final int DEFAULT_BUFFER_SIZE = 10 * 1024;
	// 60s
	public static final int DEFAULT_DEAD_TIMEOUT = 60000;

	public static final boolean DEFAULT_PROTECTED = true;

	public static final String VERSION_FLAG = "Somnus Rpc Client v1.0.0:";

	static {
		DEFAULT_CONFIG_PATH = System.getProperty("gaea.client.config.path");
		if (DEFAULT_CONFIG_PATH == null) {
			DEFAULT_CONFIG_PATH = System.getProperty("gaea.config.path");
		}
		if (DEFAULT_CONFIG_PATH == null) {
			DEFAULT_CONFIG_PATH = getJarPath(ClientConfigConstant.class) + "/gaea.config";
		}
	}

	private static String getJarPath(Class<?> type) {
		String path = type.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.replaceFirst("file:/", "");
		path = path.replaceAll("!/", "");
		path = path.replaceAll("\\\\", "/");
		path = path.substring(0, path.lastIndexOf("/"));
		if (path.substring(0, 1).equalsIgnoreCase("/")) {
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.indexOf("window") >= 0) {
				path = path.substring(1);
			}
		}
		try {
			return URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			logger.error(ClientConfigConstant.class.getName(), ex);
			return path;
		}
	}

	public static void main(String[] args) {
		logger.debug(getJarPath(ClientConfigConstant.class));

		logger.debug(DEFAULT_CONFIG_PATH);

		File file = new File(DEFAULT_CONFIG_PATH);
		System.out.println(file.exists());
	}
}
