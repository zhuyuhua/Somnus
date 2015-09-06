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
package com.somnus.utils.classz;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.utils.file.FileUtil;

import sun.misc.Launcher;

/**
 *
 * 将jar包加载到ClassPath中
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class GlobalClassLoader {

	private static Logger logger = LoggerFactory.getLogger(GlobalClassLoader.class);

	private static Method addURL;

	static {
		try {
			addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
		} catch (Exception e) {
			e.printStackTrace();
		}
		addURL.setAccessible(true);
	}

	private static URLClassLoader system = (URLClassLoader) getSystemClassLoader();

	private static URLClassLoader ext = (URLClassLoader) getExtClassLoader();

	public static ClassLoader getSystemClassLoader() {
		return ClassLoader.getSystemClassLoader();
	}

	public static ClassLoader getExtClassLoader() {
		return getSystemClassLoader().getParent();
	}

	public static void addURL2SystemClassLoader(URL url) throws Exception {
		try {
			logger.info("append jar to classpath:" + url.toString());
			addURL.invoke(system, new Object[] { url });
		} catch (Exception e) {
			throw e;
		}
	}

	public static void addURL2ExtClassLoader(URL url) throws Exception {
		try {
			logger.info("append jar to classpath:" + url.toString());
			addURL.invoke(ext, new Object[] { url });
		} catch (Exception e) {
			throw e;
		}
	}

	public static void addSystemClassPath(String path) throws Exception {
		try {
			URL url = new URL("file", "", path);
			addURL2SystemClassLoader(url);
		} catch (MalformedURLException e) {
			throw e;
		}
	}

	public static void addExtClassPath(String path) throws Exception {
		try {
			URL url = new URL("file", "", path);
			addURL2ExtClassLoader(url);
		} catch (MalformedURLException e) {
			throw e;
		}
	}

	public static void addSystemClassPathFolder(String... dirs) throws Exception {
		List<String> jarList = FileUtil.getUniqueLibPath(dirs);
		for (String jar : jarList) {
			addSystemClassPath(jar);
		}
	}

	public static void addURL2ExtClassLoaderFolder(String... dirs) throws Exception {
		List<String> jarList = FileUtil.getUniqueLibPath(dirs);
		for (String jar : jarList) {
			addExtClassPath(jar);
		}
	}

	public static URL[] getBootstrapURLs() {
		return Launcher.getBootstrapClassPath().getURLs();
	}

	public static URL[] getSystemURLs() {
		return system.getURLs();
	}

	public static URL[] getExtURLs() {
		return ext.getURLs();
	}
}
