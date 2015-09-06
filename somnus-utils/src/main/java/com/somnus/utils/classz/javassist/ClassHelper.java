/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.utils.classz.javassist;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * TODO
 *
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月18日
 */
public class ClassHelper {

	private static Logger logger = LoggerFactory.getLogger(ClassHelper.class);

	private static final String CLASS_PATH = ClassHelper.class.getResource("/").getPath();

	private static final String CLASS_PATH2 = CLASS_PATH.substring(1).replaceAll("/", "\\\\");

	public static void main(String[] args) {
		String str = "E:/svn/git/Somnus/somnus-utils/bin/";
		System.out.println(CLASS_PATH);
		System.out.println(str.replaceAll("/", "\\\\"));
	}

	/**
	 *
	 * TODO
	 *
	 * @param classPath
	 *            classpath下的包或者类， 例如com.xxx.xxx.xxx.class or com.xxx
	 * @param classLoader
	 * @return
	 */
	public static Set<Class<?>> getClassFromClass(String classPath, ClassLoader classLoader) {

		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

		getClassFromClassPath(classes, CLASS_PATH + classPath.replaceAll("\\.", "/"), classLoader);

		return classes;

	}

	private static void getClassFromClassPath(Set<Class<?>> classes, String classPath, ClassLoader classLoader) {
		File file = new File(classPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File tempFile : files) {
				getClassFromClassPath(classes, tempFile.getAbsolutePath(), classLoader);
			}
		} else {
			if (classPath.endsWith(".class")) {
				String packageName = classPath.replace(CLASS_PATH2, "");
				String className = packageName.substring(0, packageName.lastIndexOf(".class")).replaceAll("\\\\", ".");
				System.out.println(className);
				Class<?> cls = null;
				try {
					cls = classLoader.loadClass(className);
				} catch (Throwable ex) {

				}
				if (cls != null) {
					classes.add(cls);
				}
			}
		}
	}

	public static Set<Class<?>> getClassFromJar(String jarPath, ClassLoader classLoader)
			throws IOException, ClassNotFoundException {
		JarFile jarFile = new JarFile(jarPath);
		Enumeration<JarEntry> entries = jarFile.entries();
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		while (entries.hasMoreElements()) {
			JarEntry jarEntry = entries.nextElement();
			String name = jarEntry.getName();
			if (name.endsWith(".class")) {
				String className = name.replaceAll(".class", "").replaceAll("/", ".");
				Class<?> cls = null;
				try {
					// cls = classLoader.findClass(className);
					cls = classLoader.loadClass(className);
				} catch (Throwable ex) {

				}
				if (cls != null) {
					classes.add(cls);
				}
			}
		}
		return classes;
	}

	public static String[] getParamNames(Class<?> cls, Method method) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get(cls.getName());

		Class<?>[] paramAry = method.getParameterTypes();
		String[] paramTypeNames = new String[paramAry.length];
		for (int i = 0; i < paramAry.length; i++) {
			paramTypeNames[i] = paramAry[i].getName();
		}

		CtMethod cm = cc.getDeclaredMethod(method.getName(), pool.get(paramTypeNames));

		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			throw new Exception("class:" + cls.getName()
					+ ", have no LocalVariableTable, please use javac -g:{vars} to compile the source file");
		}
		String[] paramNames = new String[cm.getParameterTypes().length];
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < paramNames.length; i++) {
			paramNames[i] = attr.variableName(i + pos);
		}
		return paramNames;
	}

	public static Object[][] getParamAnnotations(Class<?> cls, Method method) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get(cls.getName());

		Class<?>[] paramAry = method.getParameterTypes();
		String[] paramTypeNames = new String[paramAry.length];
		for (int i = 0; i < paramAry.length; i++) {
			paramTypeNames[i] = paramAry[i].getName();
		}

		CtMethod cm = cc.getDeclaredMethod(method.getName(), pool.get(paramTypeNames));
		return cm.getParameterAnnotations();
	}

}
