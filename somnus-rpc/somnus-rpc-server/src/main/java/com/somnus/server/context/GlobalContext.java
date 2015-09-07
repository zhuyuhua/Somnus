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
package com.somnus.server.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.server.config.ServiceConfig;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class GlobalContext {

	private static Logger logger = LoggerFactory.getLogger(GlobalContext.class);

	private static volatile GlobalContext globalContext = null;

	private ServiceConfig serviceConfig = null;// 全局配置信息

	private ProxyFactory proxyFactory = null;

	private static final Object lockHelper = new Object();

	/**
	 * 获取单例Global
	 *
	 * @return
	 */
	public static GlobalContext getSingleton() {
		if (globalContext == null) {
			synchronized (lockHelper) {
				if (globalContext == null) {
					globalContext = new GlobalContext();
				}
			}
		}

		return globalContext;
	}

	/**
	 * serviceConfig
	 *
	 * @return serviceConfig
	 */
	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	/**
	 * @param serviceConfig
	 */
	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	/**
	 * TODO
	 *
	 * @param proxyFactory
	 */
	public void setProxyFactory(ProxyFactory proxyFactory) {
		this.proxyFactory = proxyFactory;
	}

}
