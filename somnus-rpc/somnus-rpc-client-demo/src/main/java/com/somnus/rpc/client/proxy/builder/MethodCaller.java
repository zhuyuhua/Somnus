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

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class MethodCaller {

	private static Logger logger = LoggerFactory.getLogger(MethodCaller.class);

	private String serviceName;
	private String lookup;

	public MethodCaller(String serviceName, String lookup) {
		this.serviceName = serviceName;
		this.lookup = lookup;
	}

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
		OperationContract ann = methodInfo.getAnnotation(OperationContract.class);
		if (ann != null) {
			if (!ann.methodName().equals(AnnotationUtil.DEFAULT_VALUE)) {
				methodName = "$" + ann.methodName();
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
