/*
 *  Copyright Beijing 58 Information Technology Co.,Ltd.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.bj58.spat.gaea.server.deploy.hotdeploy;

import com.bj58.spat.gaea.server.contract.context.Global;
import com.bj58.spat.gaea.server.contract.context.IProxyFactory;
import com.bj58.spat.gaea.server.deploy.bytecode.CreateManager;

/**
 * A class for dynamic load ProxyHandle
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public class ProxyFactoryLoader {

	/**
	 * 
	 * @param serviceConfig
	 * @return
	 * @throws Exception
	 */
	public static IProxyFactory loadProxyFactory(DynamicClassLoader classLoader) throws Exception {
		
		CreateManager cm = new CreateManager();
		return cm.careteProxy(Global.getSingleton().getRootPath() 
								  + "service/deploy/" 
								  + Global.getSingleton().getServiceConfig().getString("gaea.service.name"), 
							  classLoader);
	}
}