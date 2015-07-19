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
package com.somnus.utils.url;

import java.lang.reflect.Method;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * @author zhuyuhua
 * @since 2015年7月19日
 */
public class EclipseUrlParseUtils {

	private static Logger logger = LoggerFactory.getLogger(EclipseUrlParseUtils.class);

	/**
	 * 解析URL
	 * @param url
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static URL parseUrl(URL url){
		URL returnURL = null;
		try {
			Class fileLocatorClass = Class.forName("org.eclipse.core.runtime.FileLocator");
			Method resolve = fileLocatorClass.getMethod("resolve",URL.class);
			returnURL = (URL)resolve.invoke(fileLocatorClass, url);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return returnURL;
	}
	
}
