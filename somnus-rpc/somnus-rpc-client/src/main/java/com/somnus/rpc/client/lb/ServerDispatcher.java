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
package com.somnus.rpc.client.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
public class ServerDispatcher {

	private static Logger logger = LoggerFactory
			.getLogger(ServerDispatcher.class);

	private List<Server> ServerPool = new ArrayList<Server>();
	private AtomicInteger requestCount = new AtomicInteger(0);

	/**
	 * @param config
	 */
	public ServerDispatcher(ServiceConfig serviceConfig) {
		// TODO Auto-generated constructor stub
	}

	public List<Server> GetAllServer() {
		return ServerPool;
	}

	public Server GetServer(String name) {
		for (Server s : ServerPool) {
			if (s.getName().equalsIgnoreCase(name)) {
				return s;
			}
		}
		return null;
	}

	public Server GetServer() {
		// TODO Auto-generated method stub
		return null;
	}
}
