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
package com.somnus.server.classz.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.utils.file.FileUtil;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class DynamicClassLoader extends SecureClassLoader {

	private static Logger logger = LoggerFactory.getLogger(DynamicClassLoader.class);

	/**
	 * jar list load class from this
	 */
	private static List<String> jarList = new ArrayList<String>();

	/**
	 * class cache
	 */
	private Map<String, Class<?>> classCache = new HashMap<String, Class<?>>();

	public DynamicClassLoader(ClassLoader parent) {
		super(parent);
	}

	public DynamicClassLoader() {

	}

	/**
	 * dynamic find class from jar
	 *
	 * @param jarPath
	 * @param className
	 * @param fromCache
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> findClass(String jarPath, String className, boolean fromCache) throws ClassNotFoundException {

		logger.debug("find class jarPath: " + jarPath + "  className: " + className + "  fromCache:" + fromCache);

		if (fromCache && classCache.containsKey(className)) {
			return classCache.get(className);
		}

		String classPath = className.replace('.', '/').concat(".class");
		byte[] clsByte = null;
		if (jarPath == null || jarPath.equalsIgnoreCase("")) {
			for (String jp : jarList) {
				jarPath = jp;
				clsByte = getClassByte(jp, classPath);
				if (clsByte != null) {
					break;
				}
			}
			// clsByte = getClassByte(classPath);
		} else {
			clsByte = getClassByte(jarPath, classPath);
		}

		if (clsByte == null) {
			throw new ClassNotFoundException(className);
		}

		URL url = null;
		try {
			url = new URL("file", "", jarPath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return findClass(className, clsByte, url);
	}

	/**
	 *
	 * @param className
	 * @param clsByte
	 * @return
	 */
	public Class<?> findClass(String className, byte[] clsByte, URL url) {
		Class<?> cls = null;
		try {
			CodeSource cs = new CodeSource(url, (java.security.cert.Certificate[]) null);
			ProtectionDomain pd = new ProtectionDomain(cs, null, this, null);
			cls = super.defineClass(className, clsByte, 0, clsByte.length, pd);
			resolveClass(cls);
			classCache.put(className, cls);
		} catch (Exception ex) {
			logger.error("define class error", ex);
		}

		return cls;
	}

	/**
	 * dynamic find class from jar
	 *
	 * @param jarPath
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> findClass(String jarPath, String className) throws ClassNotFoundException {
		return findClass(jarPath, className, true);
	}

	/**
	 * dynamic find class from jar
	 */
	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		return findClass("", className, true);
	}

	/**
	 * dynamic find class from jar
	 *
	 * @param className
	 * @param fromCache
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> findClass(String className, boolean fromCache) throws ClassNotFoundException {
		return findClass("", className, fromCache);
	}

	/**
	 * clear all class cache
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void clearAllClassCache() {
		logger.info("clear class cache:");
		try {
			Iterator it = classCache.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Class<?>> entry = (Map.Entry<String, Class<?>>) it.next();
				logger.debug("-----key:" + entry.getKey() + "  value:" + entry.getValue().getName());
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		classCache.clear();
	}

	/**
	 * add jar url
	 *
	 * @param url
	 * @throws Exception
	 */
	public void addURL(String url) {
		if (!jarList.contains(url)) {
			jarList.add(url);
		}
	}

	/**
	 * add folder jars
	 *
	 * @param path
	 * @throws IOException
	 */
	public void addFolder(String... dirs) throws IOException {
		List<String> jarList = FileUtil.getUniqueLibPath(dirs);
		for (String jar : jarList) {
			addURL(jar);
		}
	}

	/**
	 * get jar list
	 *
	 * @return
	 */
	public List<String> getJarList() {
		return jarList;
	}

	/**
	 * get class byte from jarPath
	 *
	 * @param jarPath
	 * @param classPath
	 * @return
	 */
	private byte[] getClassByte(String jarPath, String classPath) {
		JarFile jarFile = null;
		InputStream input = null;
		byte[] clsByte = null;
		try {
			jarFile = new JarFile(jarPath); // read jar
			JarEntry entry = jarFile.getJarEntry(classPath); // read class file
			if (entry != null) {
				logger.debug("get class:" + classPath + "  from:" + jarPath);
				input = jarFile.getInputStream(entry);
				clsByte = new byte[input.available()];
				input.read(clsByte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return clsByte;
	}
}