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
package com.somnus.rpc.client.proxy.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.protocol.server.annotation.AnnotationUtil;
import com.somnus.protocol.server.annotation.Operation;
import com.somnus.protocol.server.entity.Out;
import com.somnus.rpc.client.proxy.InvokeResult;
import com.somnus.rpc.client.proxy.ServiceProxy;

/**
 *
 * 方法类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class MethodCaller {

	private static Logger logger = LoggerFactory.getLogger(MethodCaller.class);

	private String serviceName;// 服务名
	private String lookup;// 接口实现类

	public MethodCaller(String serviceName, String lookup) {
		this.serviceName = serviceName;
		this.lookup = lookup;
	}

	/**
	 * 
	 * 调用方法
	 * 
	 * @param args
	 *            方法参数
	 * @param methodInfo
	 *            方法信息
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 * @since JDK 1.6
	 */
	public Object doMethodCall(Object[] args, Method methodInfo) throws Exception, Throwable {

		Type[] typeAry = methodInfo.getGenericParameterTypes();
		Class<?>[] clsAry = methodInfo.getParameterTypes();
		if (args == null) {
			args = new Object[0];
		}
		if (args.length != typeAry.length) {
			throw new Exception("argument count error!");
		}

		ServiceProxy proxy = ServiceProxy.getProxy(serviceName);
		Parameter[] paras = new Parameter[args.length];
		List<Integer> outParas = new ArrayList<Integer>();

		if (typeAry != null) {
			for (int i = 0; i < typeAry.length; i++) {

				// TODO -这里需要修改
				if (args[i] instanceof Out) {
					paras[i] = new Parameter(args[i], clsAry[i], typeAry[i], ParaType.Out);
					outParas.add(i);
				} else {
					paras[i] = new Parameter(args[i], clsAry[i], typeAry[i], ParaType.In);
				}
			}
		}
		Parameter returnPara = new Parameter(null, methodInfo.getReturnType(), methodInfo.getGenericReturnType());
		String methodName = methodInfo.getName();
		Operation operation = methodInfo.getAnnotation(Operation.class);
		if (operation != null) {
			if (!operation.methodName().equals(AnnotationUtil.DEFAULT_VALUE)) {
				methodName = "$" + operation.methodName();
			}
		}
		InvokeResult result = proxy.invoke(returnPara, lookup, methodName, paras);

		if (result != null && result.getOutPara() != null) {
			for (int i = 0; i < outParas.size() && i < result.getOutPara().length; i++) {
				Object op = args[outParas.get(i)];
				if (op instanceof Out) {
					((Out) op).setOutPara(result.getOutPara()[i]);
				}
			}
		}
		return result.getResult();
	}
}
