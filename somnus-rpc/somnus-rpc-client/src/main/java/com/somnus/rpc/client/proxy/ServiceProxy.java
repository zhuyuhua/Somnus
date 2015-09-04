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
package com.somnus.rpc.client.proxy;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.rpc.client.config.ServiceConfig;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ServiceProxy {

	private static Logger logger = LoggerFactory.getLogger(ServiceProxy.class);

	private Dispatcher dispatcher;
	private ServiceConfig config;
	private int sessionId = 1;
	private int requestTime = 0;// 超时重连次数
	private int ioreconnect = 0;// IO服务切换次数
	private int count = 0;
	private static final Object locker = new Object();
	private static final HashMap<String, ServiceProxy> Proxys = new HashMap<String, ServiceProxy>();

	/**
	 * @param serviceName
	 */
	public ServiceProxy(String serviceName) {
		config = ServiceConfig.GetConfig(serviceName);
		dispatcher = new Dispatcher(config);

		requestTime = config.getSocketPool().getReconnectTime();
		int serverCount = 1;
		if (dispatcher.GetAllServer() != null && dispatcher.GetAllServer().size() > 0) {
			serverCount = dispatcher.GetAllServer().size();
		}

		ioreconnect = serverCount - 1;
		count = requestTime;

		if (ioreconnect > requestTime) {
			count = ioreconnect;
		}
	}

	public static ServiceProxy getProxy(String serviceName) throws Exception {
		ServiceProxy p = Proxys.get(serviceName.toLowerCase());
		if (p == null) {
			synchronized (locker) {
				p = Proxys.get(serviceName.toLowerCase());
				if (p == null) {
					p = new ServiceProxy(serviceName);
					Proxys.put(serviceName.toLowerCase(), p);
				}
			}
		}
		return p;
	}

}
