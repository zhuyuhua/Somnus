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
package com.somnus.rpc.client.proxy.builder;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.rpc.client.proxy.bean.MethodCaller;

/**
 *
 * 代理操作类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class DefaultProxyStandard implements InvocationHandler, ProxyStandard, Serializable {

	private static Logger logger = LoggerFactory.getLogger(DefaultProxyStandard.class);

	private Class<?> interfaceClass;// 接口类

	private MethodCaller methodCaller;// 调用方法

	/**
	 * @param interfaceClass
	 *            接口类
	 * @param serviceName
	 *            服务名
	 * @param lookup
	 *            接口实现类
	 */
	public DefaultProxyStandard(Class<?> interfaceClass, String serviceName, String lookup) {
		this.interfaceClass = interfaceClass;
		this.methodCaller = new MethodCaller(serviceName, lookup);
	}

	/**
	 * Object proxy：指被代理的对象。
	 * 
	 * Method method：要调用的方法
	 * 
	 * Object[] args：方法调用时所需要的参数
	 *
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = methodCaller.doMethodCall(args, method);
		long end = System.currentTimeMillis();
		long total = end - start;
		if (total > 200) {
			logger.warn("method:" + method.getName() + " invoke time :" + total);
		}
		return obj;
	}

}
