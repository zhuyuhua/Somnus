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
package com.somnus.server.classz;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.server.classz.loader.DynamicClassLoader;
import com.somnus.server.common.ClassType;
import com.somnus.server.component.annotation.Operation;
import com.somnus.server.component.annotation.Service;
import com.somnus.server.component.annotation.ServiceImpl;
import com.somnus.server.deploy.annotation.HttpPathParameter;
import com.somnus.server.deploy.annotation.HttpRequestMapping;
import com.somnus.server.deploy.bean.ClassInfo;
import com.somnus.server.deploy.bean.ContractInfo;
import com.somnus.server.deploy.bean.MethodInfo;
import com.somnus.server.deploy.bean.ParamInfo;
import com.somnus.server.deploy.bean.SessionBean;
import com.somnus.utils.classz.javassist.ClassHelper;

/**
 *
 * 类扫描
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ClassScanner {

	private static Logger logger = LoggerFactory.getLogger(ClassScanner.class);

	private static ContractInfo contractInfo = null;

	private static Object lockHelper = new Object();

	/**
	 * interface ClassInfo
	 */
	private static List<ClassInfo> interfaceInfos = null;// 接口

	/**
	 * implements ClassInfo
	 */
	private static List<ClassInfo> implementsClassInfos = null;// 实现

	/**
	 * TODO
	 *
	 * @param somnus_bean_path
	 * @param classLoader
	 * @return
	 * @throws Exception
	 */
	public static ContractInfo getContractInfo(String path, DynamicClassLoader classLoader) throws Exception {
		if (contractInfo == null) {
			synchronized (lockHelper) {
				if (contractInfo == null) {
					scan(path, classLoader);
				}
			}
		}

		return contractInfo;
	}

	/**
	 * 扫描类
	 *
	 * @param path
	 * @param classLoader
	 * @throws Exception
	 */
	private static void scan(String path, DynamicClassLoader classLoader) throws Exception {

		logger.info("begin scan class from path:" + path);

		interfaceInfos = new ArrayList<ClassInfo>();
		implementsClassInfos = new ArrayList<ClassInfo>();

		Set<Class<?>> clsSet = ClassHelper.getClassFromClass(path, classLoader);

		for (Class<?> cls : clsSet) {
			try {
				ServiceImpl serviceImpl = cls.getAnnotation(ServiceImpl.class);
				Service service = cls.getAnnotation(Service.class);
				if (serviceImpl == null && service == null) {
					continue;
				}

				if (service != null) {
					ClassInfo ci = generalInterface(cls);
					if (ci != null) {
						interfaceInfos.add(ci);
					}
				} else if (serviceImpl != null) {
					ClassInfo ci = generalImplementsClass(cls);
					if (ci != null) {
						implementsClassInfos.add(ci);
					}
				}
			} catch (Exception ex) {
				throw ex;
			}
		}
		contractInfo = createContractInfo(interfaceInfos, implementsClassInfos);

		logger.info("finish scan class");
	}

	private static ContractInfo createContractInfo(List<ClassInfo> interfaceInfos,
			List<ClassInfo> implementsClassInfos) {

		ContractInfo contractInfo = new ContractInfo();
		List<SessionBean> sessionBeanList = new ArrayList<SessionBean>();
		for (ClassInfo c : interfaceInfos) {
			SessionBean bean = new SessionBean();
			bean.setInterfaceClass(c);
			bean.setInterfaceName(c.getCls().getName());
			Map<String, String> implMap = new HashMap<String, String>();

			for (ClassInfo b : implementsClassInfos) {
				Class<?>[] interfaceAry = b.getCls().getInterfaces();
				for (Class<?> item : interfaceAry) {
					if (item == c.getCls()) {
						implMap.put(b.getLookUP(), b.getCls().getName());
						break;
					}
				}
			}
			bean.setInstanceMap(implMap);
			sessionBeanList.add(bean);
		}

		contractInfo.setSessionBeanList(sessionBeanList);
		return contractInfo;
	}

	/**
	 * 生成实现类信息
	 *
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	private static ClassInfo generalImplementsClass(Class<?> cls) throws Exception {
		ServiceImpl behaviorAnn = cls.getAnnotation(ServiceImpl.class);

		ClassInfo ci = new ClassInfo();
		ci.setCls(cls);
		ci.setClassType(ClassType.CLASS);

		// 服务名称
		if (behaviorAnn != null && !behaviorAnn.lookUP().equalsIgnoreCase(AnnotationUtil.DEFAULT_VALUE)) {
			ci.setLookUP(behaviorAnn.lookUP());
		} else {
			// 如果没有，默认取类名
			ci.setLookUP(cls.getSimpleName());
		}

		Method[] methods = cls.getDeclaredMethods();
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();

		for (Method m : methods) {
			// only load public or protected method
			if (Modifier.isPublic(m.getModifiers()) || Modifier.isProtected(m.getModifiers())) {
				MethodInfo mi = new MethodInfo();
				mi.setMethod(m);

				HttpRequestMapping requestMappingAnn = m.getAnnotation(HttpRequestMapping.class);
				mi.setHttpRequestMapping(requestMappingAnn);

				Class<?>[] paramAry = m.getParameterTypes();
				Type[] types = m.getGenericParameterTypes();

				String[] paramNames = ClassHelper.getParamNames(cls, m);
				String[] mapping = new String[paramAry.length];
				HttpPathParameter[] paramAnnAry = new HttpPathParameter[paramAry.length];

				// load RequestMapping
				if (requestMappingAnn != null) {
					Object[][] annotations = ClassHelper.getParamAnnotations(cls, m);
					for (int i = 0; i < annotations.length; i++) {
						for (int j = 0; j < annotations[i].length; j++) {
							HttpPathParameter paramAnn = null;
							try {
								paramAnn = (HttpPathParameter) annotations[i][j];
							} catch (Exception ex) {

							}

							paramAnnAry[i] = paramAnn;
							if (paramAnn != null) {
								mapping[i] = paramAnn.mapping();
								break;
							} else {
								mapping[i] = paramNames[i];
							}
						}
					}

					for (int i = 0; i < paramAry.length; i++) {
						if (mapping[i] == null) {
							mapping[i] = paramNames[i];
						}
					}
				}

				ParamInfo[] paramInfoAry = new ParamInfo[paramAry.length];
				for (int i = 0; i < paramAry.length; i++) {
					paramInfoAry[i] = new ParamInfo(i, paramAry[i], types[i], paramNames[i], mapping[i],
							paramAnnAry[i]);
				}
				mi.setParamInfoAry(paramInfoAry);

				methodInfos.add(mi);
			}
		}
		ci.setMethodList(methodInfos);

		return ci;
	}

	/**
	 * 生成接口类信息
	 *
	 * @param cls
	 * @return
	 */
	protected static ClassInfo generalInterface(Class<?> cls) {
		Service contractAnn = cls.getAnnotation(Service.class);

		ClassInfo ci = new ClassInfo();
		ci.setCls(cls);
		ci.setClassType(ClassType.INTERFACE);

		List<Class<?>> interfaceList = getInterfaces(cls);
		List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();

		for (Class<?> interfaceCls : interfaceList) {
			Method[] methods = interfaceCls.getDeclaredMethods();
			if (contractAnn != null && contractAnn.defaultAll()) {
				for (Method m : methods) {
					if (Modifier.isPublic(m.getModifiers()) || Modifier.isProtected(m.getModifiers())) {
						MethodInfo mi = new MethodInfo();
						mi.setMethod(m);
						methodInfos.add(mi);
					}
				}
			} else {
				for (Method m : methods) {
					if (Modifier.isPublic(m.getModifiers()) || Modifier.isProtected(m.getModifiers())) {
						Operation oc = m.getAnnotation(Operation.class);
						if (oc != null) {
							MethodInfo mi = new MethodInfo();
							mi.setMethod(m);
							methodInfos.add(mi);
						}
					}
				}
			}
		}

		ci.setMethodList(methodInfos);

		return ci;

	}

	/**
	 * 递归获取所有接口
	 *
	 * @param cls
	 * @return
	 */
	private static List<Class<?>> getInterfaces(Class<?> cls) {
		List<Class<?>> clsList = new ArrayList<Class<?>>();
		getInterfaces(cls, clsList);
		return clsList;
	}

	private static void getInterfaces(Class<?> cls, List<Class<?>> clsList) {
		clsList.add(cls);
		Class<?>[] aryCls = cls.getInterfaces();

		if (aryCls != null && aryCls.length > 0) {
			for (Class<?> c : aryCls) {
				getInterfaces(c, clsList);
			}
		}
	}
}
