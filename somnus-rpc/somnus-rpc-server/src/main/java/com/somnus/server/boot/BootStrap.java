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
package com.somnus.server.boot;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.server.classz.loader.DynamicClassLoader;
import com.somnus.server.classz.loader.ProxyFactoryLoader;
import com.somnus.server.common.ConfigConstant;
import com.somnus.server.config.ServiceConfig;
import com.somnus.server.context.GlobalContext;
import com.somnus.server.context.ProxyFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class BootStrap {

	private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

	public static void main(String[] args) throws Exception {

		args = new String[] { "-Dsomnus.service.name=demo" };
		if (args.length < 1) {
			throw new IllegalArgumentException("usage: -Dsomnus.service.name=<service-name> [<other-somnus-config>]");
		}
		String serviceName = null;
		Map<String, String> argsMap = new HashMap<String, String>();

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-D")) {
				String[] aryArg = args[i].split("=");
				if (aryArg.length == 2) {
					if (aryArg[0].equalsIgnoreCase("-Dsomnus.service.name")) {
						serviceName = aryArg[1];
					}
					argsMap.put(aryArg[0].replaceFirst("-D", ""), aryArg[1]);
				}
			}
		}

		logger.info("------------------load service config--------------");
		String defaultConfigFile = ConfigConstant.SERVER_CONFIG_XML;
		ServiceConfig sc = ServiceConfig.getServiceConfig(defaultConfigFile);
		GlobalContext.getSingleton().setServiceConfig(sc);
		logger.info("----------------------end-------------------------\n");

		logger.info("--------------------loading proxys-------------------");
		DynamicClassLoader classLoader = new DynamicClassLoader();

		ProxyFactory proxyFactory = ProxyFactoryLoader.loadProxyFactory(classLoader);

		GlobalContext.getSingleton().setProxyFactory(proxyFactory);
		logger.info("-------------------------end-------------------------\n");
	}
}
